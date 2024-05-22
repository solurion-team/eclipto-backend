package com.solurion.eclipto.task.event;

import com.solurion.eclipto.common.kafka.ProjectTopicConfig;
import com.solurion.eclipto.common.kafka.ProjectTopicListener;
import com.solurion.eclipto.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectTopicEventListener {
    private final TaskService taskService;

    @ProjectTopicListener(filter = ProjectTopicConfig.CREATE_PROJECT_FILTER)
    public void listenProjectCreatedEvent(Long projectId){taskService.onProjectCreated(projectId);}
    @ProjectTopicListener(filter = ProjectTopicConfig.DELETE_PROJECT_FILTER)
    public void listenProjectDeletedEvent(Long projectId){taskService.onProjectDeleted(projectId);}
}
