package com.brian.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaDemoTest {

    @Test
    public void sendMessgae() throws Exception {
        KafkaTemplate<String, String> template = createTemplate();
        int count = 10000;
        while (count > 0) {
            ProducerRecord record = new ProducerRecord<>("brian_t", UUID.randomUUID().toString(),
                    "{" + UUID.randomUUID().toString() + ":" + UUID.randomUUID().toString() + "}");
            template.send(record);
            log.info("<><><><><> send message <><><><><>: " + record.value());
            count--;
        }
    }

    @Test
    public void receiveMessgae() throws Exception {
        ContainerProperties containerProps = new ContainerProperties("brian_t");
        containerProps.setMessageListener(new MessageListener<String, String>() {
            @Override
            public void onMessage(ConsumerRecord<String, String> message) {
                log.info("===== receie message =====: " + message);
            }
        });
        KafkaMessageListenerContainer<String, String> container = createContainer(containerProps);
        container.setBeanName("KafakTest");
        container.start();
        Thread.sleep(10000);
        container.stop();
    }

    private KafkaMessageListenerContainer<String, String> createContainer(ContainerProperties containerProps) {
        Map<String, Object> props = consumerProps();
        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<String, String>(props);
        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(cf,
                containerProps);
        return container;
    }

    private KafkaTemplate<String, String> createTemplate() {
        Map<String, Object> senderProps = senderProps();
        ProducerFactory<String, String> pf = new DefaultKafkaProducerFactory<String, String>(senderProps);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(pf);
        return template;
    }

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "49.233.143.198:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer-43625");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    private Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "49.233.143.198:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
}