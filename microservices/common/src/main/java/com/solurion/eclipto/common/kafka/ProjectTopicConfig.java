package com.solurion.eclipto.common.kafka;

import org.mapstruct.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

@Configuration
public class ProjectTopicConfig {

    public static final String TOPIC = "project-topic";

    public static final String CREATE_PROJECT_KEY = "project-created";

    public static final String DELETE_PROJECT_KEY = "project-deleted";

    public static final String CREATE_PROJECT_FILTER = "createdProjectFilter";

    public static final String DELETE_PROJECT_FILTER = "deletedProjectFilter";

    @Bean(CREATE_PROJECT_FILTER)
    public RecordFilterStrategy<String, Long> createdProjectFilter() {
        return new KeyRecordFilterStrategy<>(CREATE_PROJECT_KEY);
    }

    @Bean(DELETE_PROJECT_FILTER)
    public RecordFilterStrategy<String, Long> deletedProjectFilter() {
        return new KeyRecordFilterStrategy<>(DELETE_PROJECT_KEY);
    }
}
