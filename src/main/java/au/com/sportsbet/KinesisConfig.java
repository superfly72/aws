package au.com.sportsbet;

import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by royh on 19/05/2017.
 */
@ConfigurationProperties(prefix="aws.kinesis.producer")
public class KinesisProducerConfig {

    private String REGION;
    private String 


            config.setRegion(REGION);

    // You can pass credentials programmatically through the configuration,
    // similar to the AWS SDK. DefaultAWSCredentialsProviderChain is used
    // by default, so this configuration can be omitted if that is all
    // that is needed.
        config.setCredentialsProvider(new DefaultAWSCredentialsProviderChain());

    // The maxConnections parameter can be used to control the degree of
    // parallelism when making HTTP requests. We're going to use only 1 here
    // since our throughput is fairly low. Using a high number will cause a
    // bunch of broken pipe errors to show up in the logs. This is due to
    // idle connections being closed by the server. Setting this value too
    // large may also cause request timeouts if you do not have enough
    // bandwidth.
        config.setMaxConnections(1);

    // Set a more generous timeout in case we're on a slow connection.
        config.setRequestTimeout(60000);

    // RecordMaxBufferedTime controls how long records are allowed to wait
    // in the KPL's buffers before being sent. Larger values increase
    // aggregation and reduces the number of Kinesis records put, which can
    // be helpful if you're getting throttled because of the records per
    // second limit on a shard. The default value is set very low to
    // minimize propagation delay, so we'll increase it here to get more
    // aggregation.
        config.setRecordMaxBufferedTime(15000);

    // If you have built the native binary yourself, you can point the Java
    // wrapper to it with the NativeExecutable option. If you want to pass
    // environment variables to the executable, you can either use a wrapper
    // shell script, or set them for the Java process, which will then pass
    // them on to the child process.
    // config.setNativeExecutable("my_directory/kinesis_producer");

    // If you end up using the default configuration (a Configuration instance
    // without any calls to set*), you can just leave the config argument
    // out.
    //
    // Note that if you do pass a Configuration instance, mutating that
    // instance after initializing KinesisProducer has no effect. We do not
    // support dynamic re-configuration at the moment.
    KinesisProducer producer = new KinesisProducer(config);

        return producer;

    KinesisProducerConfiguration config = new KinesisProducerConfiguration();
}
