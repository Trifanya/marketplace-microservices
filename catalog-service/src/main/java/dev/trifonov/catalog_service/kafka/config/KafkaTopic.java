package dev.trifonov.catalog_service.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Bean
    public NewTopic newFavoriteTopic(@Value("${spring.kafka.topic.favorites}") String topicName) {
        return TopicBuilder
                .name(topicName)
                .partitions(5)
                .build();
    }
}
