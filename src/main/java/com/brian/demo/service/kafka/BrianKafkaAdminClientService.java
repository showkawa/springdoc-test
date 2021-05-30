package com.brian.demo.service.kafka;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.admin.DescribeReplicaLogDirsResult.ReplicaLogDirInfo;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartitionReplica;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.ConfigResource.Type;
import org.apache.kafka.common.requests.DescribeLogDirsResponse.LogDirInfo;
import org.springframework.stereotype.Component;


// @Component
public class BrianKafkaAdminClientService {
    
    private AdminClient adminClient;

    public BrianKafkaAdminClientService() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        adminClient = KafkaAdminClient.create(props);

        // DescribeAclsResult aclResult = adminClient.describeAcls(null);

        /**
         * Delegation Token requests are not allowed on PLAINTEXT/1-way SSL channels 
         *      and on delegation token authenticated channels.
         */
        // DescribeDelegationTokenResult delegationTokenResult = adminClient.describeDelegationToken();
        // log.info("=== describeDelegationTokenResult ===: {} ", delegationTokenResult.delegationTokens().get());
    }

    public Collection<TopicListing> getTopics() throws InterruptedException, ExecutionException {
       return adminClient.listTopics().listings().get();
    }

    public Collection<ConsumerGroupListing>  getConsumerGroups() throws InterruptedException, ExecutionException {
        return adminClient.listConsumerGroups().all().get();
    }


    public Collection<Node>  getDescribeCluster() throws InterruptedException, ExecutionException {
        return adminClient.describeCluster().nodes().get();
    }   
    
    public Map<ConfigResource, Config>  getDescribeConfigs(String topicName) throws InterruptedException, ExecutionException {
        return adminClient.describeConfigs(Arrays.asList(new ConfigResource(Type.TOPIC, topicName))).all().get();
    }    
    
    public Map<String, ConsumerGroupDescription>  getDescribeConsumerGroups(String consumerGroupIds) throws InterruptedException, ExecutionException {
        return adminClient.describeConsumerGroups(Arrays.asList(consumerGroupIds)).all().get();
    }    
    
    public Map<Integer, Map<String, LogDirInfo>>  getDescribeLogDirs(List<Integer> brokers) throws InterruptedException, ExecutionException {
        return adminClient.describeLogDirs(Arrays.asList(0)).all().get();
    }    
    
    public Map<TopicPartitionReplica, ReplicaLogDirInfo>  getDescribeReplicaLogDirs(List<TopicPartitionReplica> replicas) throws InterruptedException, ExecutionException  {
        return adminClient.describeReplicaLogDirs(replicas).all().get();
    }    
    
    public Map<String, TopicDescription>  getDescribeTopics(List<String> topics) throws InterruptedException, ExecutionException {
        return adminClient.describeTopics(topics).all().get();
    }    

}
