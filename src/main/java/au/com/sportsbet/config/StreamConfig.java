package au.com.sportsbet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by royh on 22/05/2017.
 */
@Component
@ConfigurationProperties(prefix="aws.kinesis.stream")
public class StreamConfig {

    private String streamName;
    private String partitionKey;

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }
}
