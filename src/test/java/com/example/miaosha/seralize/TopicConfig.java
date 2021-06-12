package com.example.miaosha.seralize;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@TestConfiguration
public class TopicConfig {
    @Value("${spring.kafka.topic-name}")
    private String topicName;

    @Value("${spring.kafka.partitions}")
    private int partitions;

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(topicName).partitions(partitions).build();
    }
}
