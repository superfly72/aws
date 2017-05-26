package au.com.demo.service;


import au.com.demo.config.ProducerConfig;
import au.com.demo.config.SecurityConfig;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import com.amazonaws.services.kinesis.producer.UserRecordResult;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

/**
 * Created by royh on 19/05/2017.
 */
@Service
public class ProducerService {

    private final Logger log = LoggerFactory.getLogger(ProducerService.class);

    private final SecurityConfig securityConfig;
    private final ProducerConfig producerConfig;
    private final KinesisProducer kinesisProducer;

    @Autowired
    public ProducerService(ProducerConfig pConfig, SecurityConfig sConfig) {
        log.info("Constructor using profile {}", sConfig.getProfile());
        this.producerConfig = pConfig;
        this.securityConfig = sConfig;
        KinesisProducerConfiguration config = new KinesisProducerConfiguration();
        config.setCredentialsProvider(new ProfileCredentialsProvider(securityConfig.getProfile()));
        config.setRecordMaxBufferedTime(producerConfig.getRecordMaxBufferedTime());
        config.setRequestTimeout(producerConfig.getRequestTimeout());
        config.setRegion(producerConfig.getRegion());
        config.setMaxConnections(producerConfig.getMaxConnections());
        config.setRecordTtl(producerConfig.getRecordTtl());
        config.setAggregationEnabled(producerConfig.isAggregationEnabled());
        kinesisProducer = new KinesisProducer(config);
    }


    public UserRecordResult publishSynchronously (String payload, String streamName, String partitionKey) throws Exception {

        log.info("publishSynchronously...");
        ByteBuffer data = ByteBuffer.wrap(payload.getBytes("UTF-8"));
        ListenableFuture<UserRecordResult> listenableFuture =
                kinesisProducer.addUserRecord(streamName, partitionKey, data);

        //BLOCKING
        while (!listenableFuture.isDone() ) {
            UserRecordResult result = listenableFuture.get();
            log.info("published. SequenceNumber {} ", result.getSequenceNumber());
            return result;
        }
        throw new RuntimeException("Failed to publishSynchronously");
    }


    public void publishAsynchronously (String payload, String streamName, String partitionKey, FutureCallback callback) throws Exception {

        log.info("publishAsynchronously...");
        ByteBuffer data = ByteBuffer.wrap(payload.getBytes("UTF-8"));
        ListenableFuture<UserRecordResult> listenableFuture =
                kinesisProducer.addUserRecord(streamName, partitionKey, data);
        Futures.addCallback(listenableFuture, callback);
    }
}
