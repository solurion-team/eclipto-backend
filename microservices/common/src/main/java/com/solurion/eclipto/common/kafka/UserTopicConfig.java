package com.solurion.eclipto.common.kafka;

import org.mapstruct.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

@Configuration
public class UserTopicConfig {
    public static final String TOPIC = "user-topic";

    public static final String DELETE_USER_KEY = "delete-user";

    public static final String DELETE_USER_FILTER = "deleteUserFilter";

    @Bean(DELETE_USER_FILTER)
    public RecordFilterStrategy<String, Long> deleteUserFilter() {
        return new KeyRecordFilterStrategy<>(DELETE_USER_KEY);
    }
}