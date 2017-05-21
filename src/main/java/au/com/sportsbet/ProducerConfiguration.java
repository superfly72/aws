package au.com.sportsbet;

import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by royh on 19/05/2017.
 */
@ConfigurationProperties(prefix="aws.kinesis.producer")
public class ProducerConfiguration {

    public String REGION;
    public int MAX_CONNECTIONS;
    public int REQUEST_TIMEOUT;
    public int RECORD_MAX_BUFFERED_TIME;


    public ProducerConfiguration() {

    }

    public KinesisProducerConfiguration getConfig() {
        KinesisProducerConfiguration config = new KinesisProducerConfiguration();
        config.setRegion(REGION);
        config.setMaxConnections(MAX_CONNECTIONS);
        config.setRequestTimeout(REQUEST_TIMEOUT);
        config.setRecordMaxBufferedTime(RECORD_MAX_BUFFERED_TIME);
        return config;
    }


}
