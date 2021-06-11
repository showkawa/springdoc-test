package com.brian.demo.kafka;

import com.brian.demo.service.kafka.BrianKafkaProducer;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BrianKafkaProducerTest {

    
    @Test
    public void When_SendMessage_Expect_Callback() throws Exception {
        BrianKafkaProducer brianKafkaProducer1= new BrianKafkaProducer();
        if(brianKafkaProducer1.initProducer()){
            brianKafkaProducer1.sendMessageWithCallback();
        }

        BrianKafkaProducer brianKafkaProducer2= new BrianKafkaProducer();
        if(brianKafkaProducer2.initProducer()){
            brianKafkaProducer2.sendMessageWithCallback();
        }
    }
    
}
