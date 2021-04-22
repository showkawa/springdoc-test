package com.brian.demo.kafka;

import com.brian.demo.service.kafka.BrianKafkaProducer;

import org.junit.Test;

public class BrianKafkaProducerTest {

    private BrianKafkaProducer brianKafkaProducer;
    
    @Test
    public void When_SendMessage_Expect_Callback() throws Exception {
        brianKafkaProducer= new BrianKafkaProducer();
        brianKafkaProducer.sendMessageWithCallback();
    }
    
}
