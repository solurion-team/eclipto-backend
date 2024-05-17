package com.solurion.eclipto.task.event;

import com.solurion.eclipto.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectTopicEventListener {
    private final TaskService taskService;

    public void listenProjectCreatedEvent(Long projectId){taskService.onProjectCreated(projectId);};

    public void listenProjectDeletedEvent(Long projectId){taskService.onProjectDeleted(projectId);};
}
