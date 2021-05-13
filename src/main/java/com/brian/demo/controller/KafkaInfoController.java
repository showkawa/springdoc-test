package com.brian.demo.controller;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListConsumerGroupsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class KafkaInfoController {

    private AdminClient adminClient;

    public KafkaInfoController() throws InterruptedException, ExecutionException{
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        adminClient = KafkaAdminClient.create(props);
        ListTopicsResult tr = listTopic();
        Map<String, TopicListing> result = tr.namesToListings().get();
        result.keySet().forEach(key -> {
            log.info("=== topicInfo ===: {} ,{}", key, result.get(key).name());
        });
        
    }

    public ListTopicsResult listTopic() {
       return adminClient.listTopics();
    }

    public ListConsumerGroupsResult listConsumerGroups() {
        return adminClient.listConsumerGroups();
     }
}
