package com.brian.demo.kafka;

import com.brian.demo.service.kafka.BrianKafkaConsumerManualCommit;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BrianKafkaConsumerManualCommitTest {
    
    @Test
    public void When_GetMessage_Expect_Success() throws Exception {
        // new BrianKafkaConsumerManualCommit("BC-1005").getMessageManualCommit();
        // new BrianKafkaConsumerManualCommit("BC-1006").getMessageManualCommitWithOffser();
        new BrianKafkaConsumerManualCommit("BC-1007").getMessageManualAsyncCommit();
    }
    
}

