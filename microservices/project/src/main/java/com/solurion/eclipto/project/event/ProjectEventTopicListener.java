package com.solurion.eclipto.project.event;

import com.solurion.eclipto.common.kafka.UserTopicConfig;
import com.solurion.eclipto.common.kafka.UserTopicListener;
import com.solurion.eclipto.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectEventTopicListener {
    private final ProjectService projectService;
    @UserTopicListener(filter = UserTopicConfig.DELETE_USER_FILTER)
    public void listenProjectTopicListener(Long userId){
        projectService.onUserDeleted(userId);
    }
}
