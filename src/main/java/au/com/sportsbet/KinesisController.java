package au.com.sportsbet;

import com.amazonaws.services.kinesis.producer.Attempt;
import com.amazonaws.services.kinesis.producer.UserRecordFailedException;
import com.amazonaws.services.kinesis.producer.UserRecordResult;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by roy on 21/05/17.
 */
@RestController
@RequestMapping("kinesis")
public class KinesisController {

    private final Logger log = LoggerFactory.getLogger(KinesisController.class);

    private final SBKinesisClient sbKinesisClient;

    @Autowired
    public KinesisController (SBKinesisClient c) {
        this.sbKinesisClient = c;
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody String publish(@RequestBody String payload) {
        byte[] bytes = payload.getBytes(Charset.defaultCharset());
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        ListenableFuture<UserRecordResult> listenableFuture = sbKinesisClient.getKinesisProducer()
                .addUserRecord(sbKinesisClient.getStreamName(),
                        sbKinesisClient.getPartitionKey(),
                        Utils.randomExplicitHashKey(),
                        buffer);

        Futures.addCallback(listenableFuture, callback);
        return "yo";

    }

    final AtomicLong completed = new AtomicLong(0);

    final FutureCallback<UserRecordResult> callback = new FutureCallback<UserRecordResult>() {
        @Override
        public void onFailure(Throwable t) {
            // We don't expect any failures during this sample. If it
            // happens, we will log the first one and exit.
            if (t instanceof UserRecordFailedException) {
                Attempt last = Iterables.getLast(
                        ((UserRecordFailedException) t).getResult().getAttempts());
                log.error(String.format(
                        "Record failed to put - %s : %s",
                        last.getErrorCode(), last.getErrorMessage()));
            }
            log.error("Exception during put", t);
            System.exit(1);
        }

        @Override
        public void onSuccess(UserRecordResult result) {
            completed.getAndIncrement();
        }
    };
}
