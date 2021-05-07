package com.brian.demo.service.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BrianKafkaConsumerManualCommit {

    /**
     * KafkaConsuner is Non-thread safe
     */
    private KafkaConsumer<String, String> consumer;

    public BrianKafkaConsumerManualCommit(String clientId) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer-43625");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        consumer = new KafkaConsumer<String, String>(props);
    }

    public void getMessageManualCommit() {
        consumer.subscribe(Arrays.asList("brian_t"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            if (records.isEmpty()) {
                log.info("===== topic: brian_t not have data! =====");
                continue;
            }

            records.forEach(r -> {
                log.info("{ key:{}, value:{}, offset:{} }", r.key(), r.value(), r.offset());
            });

            // manual commit
            consumer.commitSync();
            log.info("===== manual commit the offset =====");
        }
    }

    public void getMessageManualCommitWithOffser() {
        consumer.subscribe(Arrays.asList("brian_t"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            if (records.isEmpty()) {
                log.info("===== topic: brian_t not have data! =====");
                continue;
            }
            records.forEach(r -> {
               log.info("{ key:{}, value:{}, offset:{} }", r.key(), r.value(), r.offset());
               TopicPartition tp = new TopicPartition(r.topic(), r.partition());
                // commit with offset
                consumer.commitSync(Collections.singletonMap(tp, new OffsetAndMetadata(r.offset()+1)));
                log.info("===== manual commit the offset =====");
            });
        }
    }

    public void getMessageManualAsyncCommit() {
        consumer.subscribe(Arrays.asList("brian_t"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            if (records.isEmpty()) {
                log.info("===== topic: brian_t not have data! =====");
                continue;
            }
            records.forEach(r -> {
               log.info("{ key:{}, value:{}, offset:{} }", r.key(), r.value(), r.offset());
               TopicPartition tp = new TopicPartition(r.topic(), r.partition());
                // commit with offset
                consumer.commitAsync(Collections.singletonMap(tp, new OffsetAndMetadata(r.offset()+1)), new OffsetCommitCallback() {

                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        if(exception == null){
                            log.info("===== manual commit the offset success =====: {}", offsets.size());
                        } else {
                            log.info("===== manual commit error =====: {}", exception.getMessage());
                        }
                    }
                });
            });
        }
    }




}
