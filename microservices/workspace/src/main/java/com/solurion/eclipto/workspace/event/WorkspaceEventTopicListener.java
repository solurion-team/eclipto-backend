package com.solurion.eclipto.workspace.event;


import com.solurion.eclipto.common.kafka.UserTopicConfig;
import com.solurion.eclipto.common.kafka.UserTopicListener;
import com.solurion.eclipto.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WorkspaceEventTopicListener {
    private final WorkspaceService workspaceService;

    @UserTopicListener(filter = UserTopicConfig.DELETE_USER_FILTER)
    public void listenWorkspaceTopicListener(Long userId){
        workspaceService.onUserDeleted(userId);
    };


}
