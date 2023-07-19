package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerTest {

    public static final String TOPIC = "kafka-test";
    public static final String BROKER_LIST = "localhost:9092";


    public static Properties initConfig() {
        Properties props = new Properties();
        props.put("bootstarp.servers", BROKER_LIST);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "hello,kafka");
        Future<RecordMetadata> metadataFuture =  producer.send(record);

    }

    public static Properties initConsumerConfig() {
        Properties props = new Properties();
        props.put("bootstarp.servers", BROKER_LIST);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    public static void consumer() {
        Properties props = initConsumerConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC));

        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {

                }
            }
        } catch (Exception e) {

        } finally {
            consumer.close();
        }



    }
}
