package com.solurion.eclipto.task.api;

import com.solurion.eclipto.task.dto.*;
import com.solurion.eclipto.task.service.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi{
    private final TaskServiceImpl taskService;

    @Override
    public ResponseEntity<List<TaskLiteDto>> getLiteTasks(Long projectId) {
        return ResponseEntity.ok(taskService.getLiteTasks(projectId));
    }

    @Override
    public ResponseEntity<TaskInfoDto> getTask(Long projectId, Long taskId) {
        return ResponseEntity.ok(taskService.getTaskInfo(projectId, taskId));
    }

    @Override
    public ResponseEntity<Void> postLiteTask(Long projectId, PostLiteTaskRequest postLiteTaskRequest) {
        taskService.createTask(projectId, postLiteTaskRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> postTaskStatus(Long projectId, PostTaskStatusRequest postTaskStatusRequest) {
        taskService.createTaskStatus(projectId, postTaskStatusRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateTask(Long projectId, UpdateTaskRequest updateTaskRequest) {
        taskService.updateTask(projectId, updateTaskRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateTaskStatus(Long projectId, TaskStatusDto taskStatusDto) {
        taskService.updateTaskStatus(projectId, taskStatusDto);
        return ResponseEntity.noContent().build();
    }
}
