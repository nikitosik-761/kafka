package org.bookpublisher.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${my.kafka.topic}")
    private String topic;

    @Bean
    public NewTopic booksPublishedTopic() {
        return TopicBuilder.name(topic)
                .partitions(10)
                .replicas(1)
                .build();
    }


}
