package com.brian.demo.controller;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.DescribeConsumerGroupsResult;
import org.apache.kafka.clients.admin.DescribeLogDirsResult;
import org.apache.kafka.clients.admin.DescribeReplicaLogDirsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListConsumerGroupsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.TopicPartitionReplica;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.ConfigResource.Type;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class KafkaInfoController {

    private AdminClient adminClient;

    public KafkaInfoController() throws InterruptedException, ExecutionException{
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"49.233.143.198:9092");
        adminClient = KafkaAdminClient.create(props);
        ListTopicsResult tr = listTopic();
        log.info("=== topics ===: {} ", tr.names().get());
        log.info("=== listings ===: {} ", tr.listings().get());


        ListConsumerGroupsResult lcg = listConsumerGroups();
        log.info("=== consumerGroupListing ===: {} ", lcg.all().get());

        // DescribeAclsResult aclResult = adminClient.describeAcls(null);

        DescribeClusterResult clusterResult = adminClient.describeCluster();
        log.info("=== describeAclsResult ===: {} ", clusterResult.nodes().get());


        DescribeConfigsResult configResult = adminClient.describeConfigs(Arrays.asList(new ConfigResource(Type.TOPIC, "brian_t")));
        log.info("=== describeConfigsResult ===: {} ", configResult.all().get());


        DescribeConsumerGroupsResult consumerGroupsResult = adminClient.describeConsumerGroups(Arrays.asList("console-consumer-43625"));
        log.info("=== describeConsumerGroupsResult ===: {} ", consumerGroupsResult.all().get());


        /**
         * Delegation Token requests are not allowed on PLAINTEXT/1-way SSL channels 
         *      and on delegation token authenticated channels.
         */
        // DescribeDelegationTokenResult delegationTokenResult = adminClient.describeDelegationToken();
        // log.info("=== describeDelegationTokenResult ===: {} ", delegationTokenResult.delegationTokens().get());

        DescribeLogDirsResult logDirsResult = adminClient.describeLogDirs(Arrays.asList(0));
        log.info("=== describeLogDirsResult ===: {} ", logDirsResult.all().get());


        DescribeReplicaLogDirsResult describeReplicaLogDirsResult = 
                adminClient.describeReplicaLogDirs(Arrays.asList(new TopicPartitionReplica("brian_t", 0, 0)));
                log.info("=== describeReplicaLogDirs ===: {} ", describeReplicaLogDirsResult.all().get());
        
        
        DescribeTopicsResult describeTopics = adminClient.describeTopics(Arrays.asList("brian_t"));
        log.info("=== describeTopics ===: {} ", describeTopics.all().get());
        
    }

    public ListTopicsResult listTopic() {
       return adminClient.listTopics();
    }

    public ListConsumerGroupsResult listConsumerGroups() {
        return adminClient.listConsumerGroups();
     }
}
