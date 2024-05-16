package com.solurion.eclipto.task.event;

import com.solurion.eclipto.common.kafka.UserTopicConfig;
import com.solurion.eclipto.common.kafka.UserTopicListener;
import com.solurion.eclipto.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTopicEventListener {
    private final TaskService taskService;

    @UserTopicListener(filter = UserTopicConfig.DELETE_USER_FILTER)
    public void listenDeleteUserEvent(Long userId) {
        taskService.onUserDeleted(userId);
    }
}
