package com.brian.demo.service.kafka;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrianKafkaProducer {

    private final CountDownLatch cdl = new CountDownLatch(500);

    private KafkaProducer<String, String> prod;

    public synchronized boolean initProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prod = new KafkaProducer<>(props);
        return true;
    }

    public void sendMessageWithCallback() throws InterruptedException {
        if (prod == null) {
            log.info("===== init producer failed! =====");
            return;
        }
        prod.send(new ProducerRecord<>("brian_t", getStr(), getValue()), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (metadata != null) {
                    log.info("=== send mesage success: {}", metadata.toString());
                } else {
                    log.info("=== send mesage error: {}", exception.getMessage());
                }
            }
        });
        cdl.countDown();
        if (cdl.getCount() > 0) {
            this.sendMessageWithCallback();
        }
        cdl.await();
        prod.close();
    }

    private String getStr() {
        return UUID.randomUUID().toString();
    }

    private String getValue() {
        return String.format("{%s:%s}", getStr(), getStr());
    }
}