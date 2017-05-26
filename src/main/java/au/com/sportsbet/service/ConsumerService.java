package au.com.sportsbet.service;


import au.com.sportsbet.config.ConsumerConfig;
import au.com.sportsbet.config.SecurityConfig;
import au.com.sportsbet.config.StreamConfig;
import au.com.sportsbet.model.log.ApacheAccessLog;
import au.com.sportsbet.model.tealium.IOSEvent;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessorFactory;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import com.amazonaws.services.kinesis.clientlibrary.types.InitializationInput;
import com.amazonaws.services.kinesis.clientlibrary.types.ProcessRecordsInput;
import com.amazonaws.services.kinesis.clientlibrary.types.ShutdownInput;
import com.amazonaws.services.kinesis.model.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

/**
 * Created by royh on 23/05/2017.
 */
@Service
@EnableAsync (proxyTargetClass=true)
public class ConsumerService  implements IRecordProcessorFactory{

    private final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    private final SecurityConfig securityConfig;
    private final ConsumerConfig consumerConfig;
    private final StreamConfig streamConfig;
    private final Worker worker;

    @Autowired
    public ConsumerService (ConsumerConfig cConfig, SecurityConfig sConfig, StreamConfig stConfig) {
        super();
        log.info("Constructor using profile {}", sConfig.getProfile());

        this.consumerConfig = cConfig;
        this.securityConfig = sConfig;
        this.streamConfig = stConfig;


        final KinesisClientLibConfiguration config = new KinesisClientLibConfiguration(
                consumerConfig.getApplicationName(),
                streamConfig.getStreamName(),
                new ProfileCredentialsProvider(securityConfig.getProfile()),
                consumerConfig.getWorkerId())
                .withRegionName(consumerConfig.getRegion())
                .withInitialPositionInStream(InitialPositionInStream.LATEST);


        worker = new Worker.Builder()
                .recordProcessorFactory(this)
                .config(config)
                .build();

    }


    /**
     * Create async method to start the AWS worker (RecordProcessor) in a new thread
     */
    @Async
    public void startWorker() {
        log.info("Starting worker..");
        worker.run();
    }



    @Override
    public IRecordProcessor createProcessor() {
        return new RecordProcessor();
    }


    /**
     * One instance of RecordProcessor is created for every shard in the stream.
     * All instances of RecordProcessor share state by capturing variables from
     * the enclosing SampleConsumer instance. This is a simple way to combine
     * the data from multiple shards.
     */
    private class RecordProcessor implements IRecordProcessor {

        @Override
        public void initialize(InitializationInput initializationInput) {
            log.info("initialize ..");
        }

        @Override
        public void processRecords(ProcessRecordsInput processRecordsInput) {
            log.info("Processing records...");
            for (Record r : processRecordsInput.getRecords()) {

                // Extract the sequence number. It's encoded as a decimal
                // string and placed at the beginning of the record data,
                // followed by a space. The rest of the record data is padding
                // that we will simply discard.
                try {
                    ByteBuffer bb = r.getData();
                    String json = new String(bb.array(), "UTF-8");
                    IOSEvent iosEvent = new IOSEvent().fromJson(json);
                    iosEvent.setSequenceNumber(r.getSequenceNumber());
                    log.info("Record data: {}", iosEvent.toString());

                } catch (Exception e) {
                    log.error("Error parsing record", e);
                    System.exit(1);
                }
            }
        }

        @Override
        public void shutdown(ShutdownInput shutdownInput) {
            log.info("shutdown ..");
        }
    }
}
