package com.brian.demo.service.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BrianKafkaConsumer {

    /**
     * KafkaConsuner is Non-thread safe
     */
    private KafkaConsumer<String, String> consumer;

    public void getMessage() {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer-43625");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("brian_t"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            records.forEach(r -> {
                // log.info("{ headers:{}, topic:{}, key:{}, value:{}, offset:{} }",
                // r.headers().toString(), r.topic(), r.key(), r.value(), r.offset());
                log.info("{ key:{}, value:{}, offset:{} }", r.key(), r.value(), r.offset());
            });
        }
    }

    public void stopConsumer() {
        consumer.close(Duration.ofSeconds(3));
    }
}
