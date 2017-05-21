package au.com.sportsbet;


import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by royh on 19/05/2017.
 */
@ConfigurationProperties(prefix="aws.kinesis.producer")
public class SBKinesisClient {

    private final Logger log = LoggerFactory.getLogger(SBKinesisClient.class);
    private final String TIMESTAMP = Long.toString(System.currentTimeMillis());

    public int DATA_SIZE;
    public int SECONDS_TO_RUN;
    public int RECORDS_PER_SECOND;
    public String PARTITION_KEY;
    public String STREAM_NAME;
    public String REGION;
    public int MAX_CONNECTIONS;
    public int REQUEST_TIMEOUT;
    public int RECORD_MAX_BUFFERED_TIME;

    public KinesisProducer getKinesisProducer() {
        KinesisProducerConfiguration config = new KinesisProducerConfiguration();
        config.setRecordMaxBufferedTime(RECORD_MAX_BUFFERED_TIME);
        config.setRequestTimeout(REQUEST_TIMEOUT);
        config.setRegion(REGION);
        config.setMaxConnections(MAX_CONNECTIONS);
        KinesisProducer producer = new KinesisProducer(config);
        return producer;
    }

}
