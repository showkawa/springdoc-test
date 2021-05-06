package com.brian.demo.service.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
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

    public BrianKafkaConsumer(String clientId) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer-43625");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        consumer = new KafkaConsumer<String, String>(props);
    }

    public void getMessageBySubscribe() {
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
        }
    }

    public void getMessageByAssign() {
       TopicPartition tp1 = new TopicPartition("brian_t",0);
       TopicPartition tp2 = new TopicPartition("brian_t",1);
        consumer.assign(Arrays.asList(tp1, tp2));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            if (records.isEmpty()) {
                log.info("===== topic: brian_t not have data! =====");
                continue;
            }

            records.forEach(r -> {
                log.info("{ key:{}, value:{}, offset:{} }", r.key(), r.value(), r.offset());
            });
        }
    }

    public void getMessageWithRebalance() {
        consumer.subscribe(Arrays.asList("brian_t"), new ConsumerRebalanceListener(){

            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                log.info("<><> Before start consume the message <><>");
                
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                log.info("<><> After stop consume the message <><>");
                
            }
            
        });

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            if (records.isEmpty()) {
                log.info("===== topic: brian_t not have data! =====");
                continue;
            }

            records.forEach(r -> {
                log.info("{ key:{}, value:{}, offset:{} }", r.key(), r.value(), r.offset());
            });
        }
    }

}
