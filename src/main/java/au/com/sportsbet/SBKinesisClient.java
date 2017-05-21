package au.com.sportsbet;


import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by royh on 19/05/2017.
 */
@Component
public class SBKinesisClient {


    ProducerConfig producerConfig;

    KinesisProducer kinesisProducer;

    @Autowired
    public SBKinesisClient(ProducerConfig producerConfig) {
        this.producerConfig = producerConfig;
        kinesisProducer = new KinesisProducer(producerConfig.getKinesisProducerConfiguration());
    }

    public KinesisProducer getKinesisProducer() {
        return kinesisProducer;
    }

}
