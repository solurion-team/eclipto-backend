package com.solurion.eclipto.project.event;

import com.solurion.eclipto.common.kafka.WorkspaceTopicConfig;
import com.solurion.eclipto.common.kafka.WorkspaceTopicListener;
import com.solurion.eclipto.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkspaceEventTopicListener {
    private final ProjectService projectService;

    @WorkspaceTopicListener(id = "workspaceDeleteEvent", filter = WorkspaceTopicConfig.DELETE_WORKSPACE_FILTER)
    public void listenWorkspaceDeletedEvent(Long workspaceId) {
        projectService.onWorkspaceDeleted(workspaceId);
    }
}
