package com.brian.demo.kafka;

import com.brian.demo.service.kafka.BrianKafkaConsumer;

import org.junit.Test;

public class BrianKafkaConsumerTest {
    
    @Test
    public void When_GetMessage_Expect_Success() throws Exception {

        BrianKafkaConsumer brianKafkaConsumer= new BrianKafkaConsumer();
        brianKafkaConsumer.getMessage();
        // Thread.sleep(1000);
        // brianKafkaConsumer.stopConsumer();
    

    }
    
}
