package com.solurion.eclipto.user.config;

import com.solurion.eclipto.common.kafka.UserTopicConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic userTopic() {
        return new NewTopic(UserTopicConfig.TOPIC, 1, (short) 1);
    }
}
