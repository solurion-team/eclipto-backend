package com.solurion.eclipto.common.kafka;

import org.mapstruct.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

@Configuration
public class WorkspaceTopicConfig {

    public static final String TOPIC = "workspace-topic";

    public static final String DELETE_WORKSPACE_KEY = "workspace-deleted";

    public static final String DELETE_WORKSPACE_FILTER = "deleteWorkspaceFilter";

    @Bean
    @Named(DELETE_WORKSPACE_FILTER)
    public RecordFilterStrategy<String, String> deleteWorkspaceFilter() {
        return new KeyRecordFilterStrategy<>(DELETE_WORKSPACE_KEY);
    }
}
