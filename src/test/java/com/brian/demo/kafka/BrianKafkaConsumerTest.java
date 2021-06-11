package com.brian.demo.kafka;

import com.brian.demo.service.kafka.BrianKafkaConsumer;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BrianKafkaConsumerTest {
    
    @Test
    public void When_GetMessage_Expect_Success() throws Exception {
        // new BrianKafkaConsumer("BC-1001").getMessageBySubscribe();
        // new BrianKafkaConsumer("BC-1002").getMessageByAssign();
        new BrianKafkaConsumer("BC-1003").getMessageWithRebalance();
    }
    
}
