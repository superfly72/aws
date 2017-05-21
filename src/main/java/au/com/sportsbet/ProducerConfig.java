package au.com.sportsbet;

import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by roy on 21/05/17.
 */
@Component
@ConfigurationProperties(prefix="aws.kinesis.producer")
public class ProducerConfig {

    private int dataSize;
    private String partitionKey;
    private String streamName;
    private String region;
    private int maxConnections;
    private int requestTimeout;
    private int recordMaxBufferedTime;
    private KinesisProducerConfiguration kinesisProducerConfiguration;

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getRecordMaxBufferedTime() {
        return recordMaxBufferedTime;
    }

    public void setRecordMaxBufferedTime(int recordMaxBufferedTime) {
        this.recordMaxBufferedTime = recordMaxBufferedTime;
    }

    public KinesisProducerConfiguration getKinesisProducerConfiguration () {
        kinesisProducerConfiguration = new KinesisProducerConfiguration();
        kinesisProducerConfiguration.setRecordMaxBufferedTime(recordMaxBufferedTime);
        kinesisProducerConfiguration.setRequestTimeout(requestTimeout);
        kinesisProducerConfiguration.setRegion(region);
        kinesisProducerConfiguration.setMaxConnections(maxConnections);
        return kinesisProducerConfiguration;
    }
}
