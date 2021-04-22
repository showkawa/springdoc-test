package com.brian.demo.service.kafka;

import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BrianKafkaProducer {

    private static Properties props;

    static {
        props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "49.233.143.198:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    public void sendMessageWithCallback() {
        KafkaProducer<String, String> prod = new KafkaProducer<>(props);

        for(int i =0 ;i<10;i++){
            prod.send(new ProducerRecord<>("brian_t", getStr(), getValue()), new Callback() {

                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (metadata != null) {
                        log.info("=== send mesage success: {}", metadata.toString());
                    }
                }
            });
        }
        prod.close();
    }

    private String getStr() {
        return UUID.randomUUID().toString();
    }

    private String getValue() {
        return String.format("{{}:{}}", getStr(), getStr());
    }
}