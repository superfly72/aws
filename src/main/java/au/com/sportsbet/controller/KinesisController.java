package au.com.sportsbet.controller;

import au.com.sportsbet.config.StreamConfig;
import au.com.sportsbet.service.ConsumerService;
import au.com.sportsbet.service.ProducerService;
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
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by roy on 21/05/17.
 */
@RestController
@RequestMapping("kinesis")
public class KinesisController {

    private final Logger log = LoggerFactory.getLogger(KinesisController.class);

    private final ProducerService producerService;
    private final ConsumerService consumerService;
    private final StreamConfig streamConfig;

    @Autowired
    public KinesisController (ProducerService pService, ConsumerService cService, StreamConfig sConfig) {
        this.producerService = pService;
        this.consumerService = cService;
        consumerService.startWorker();
        this.streamConfig = sConfig;
    }

    @RequestMapping(
            value="async",
            method=RequestMethod.POST)
    public @ResponseBody String publishAsync(@RequestBody String payload) {

        log.info("Async putRecord...");
        try {
            producerService.publishAsynchronously(
                    payload,
                    streamConfig.getStreamName(),
                    streamConfig.getPartitionKey(),
                    callback);
        } catch (Exception e) {
            return "Failed " + e.getMessage();
        }
        return "future created.." ;

    }

    @RequestMapping(
            value="sync",
            method=RequestMethod.POST)
    public @ResponseBody String publishSync(@RequestBody String payload) {

        log.info("Sync putRecord...");
        try {
            UserRecordResult result = producerService.publishSynchronously(
                    payload,
                    streamConfig.getStreamName(),
                    streamConfig.getPartitionKey());
            completed.getAndIncrement();
            return result.getSequenceNumber();
        } catch (Exception e) {
            return "Failed " + e.getMessage();
        }
    }

    @RequestMapping(
            value="count",
            method=RequestMethod.GET)
    public @ResponseBody AtomicLong getCompleted() {
        log.info("getCompleted...");
        return completed;
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
            log.info("Callback success. SequenceNumber: {}", result.getSequenceNumber());
            completed.getAndIncrement();
        }
    };
}
