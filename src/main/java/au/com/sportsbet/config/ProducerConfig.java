package au.com.sportsbet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by roy on 21/05/17.
 */
@Component
@ConfigurationProperties(prefix="aws.kinesis.producer")
public class ProducerConfig {

    private String region;
    private int maxConnections;
    private int requestTimeout;
    private int recordMaxBufferedTime;
    private boolean aggregationEnabled;
    private int recordTtl;

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

    public boolean isAggregationEnabled() {
        return aggregationEnabled;
    }

    public void setAggregationEnabled(boolean aggregationEnabled) {
        this.aggregationEnabled = aggregationEnabled;
    }

    public int getRecordTtl() {
        return recordTtl;
    }

    public void setRecordTtl(int recordTtl) {
        this.recordTtl = recordTtl;
    }
}
