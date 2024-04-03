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
    public ResponseEntity<List<TaskLiteDto>> getLiteTasks(Integer projectId) {
        return ResponseEntity.ok(taskService.getLiteTasks(projectId));
    }

    @Override
    public ResponseEntity<TaskInfoDto> getTask(Integer projectId, Integer taskId) {
        return ResponseEntity.ok(taskService.getTaskInfo(projectId, taskId));
    }

    @Override
    public ResponseEntity<Void> postLiteTask(Integer projectId, PostLiteTaskRequest postLiteTaskRequest) {
        return ResponseEntity.ok(taskService.createTask(projectId, postLiteTaskRequest));
    }

    @Override
    public ResponseEntity<Void> postTaskStatus(Integer projectId, PostTaskStatusRequest postTaskStatusRequest) {
        return ResponseEntity.ok(taskService.createTaskStatus(projectId, postTaskStatusRequest));
    }

    @Override
    public ResponseEntity<Void> updateTask(Integer projectId, UpdateTaskRequest updateTaskRequest) {
        return ResponseEntity.ok(taskService.updateTask(projectId, updateTaskRequest));
    }

    @Override
    public ResponseEntity<Void> updateTaskStatus(Integer projectId, TaskStatusDto taskStatusDto) {
        return ResponseEntity.ok(taskService.updateTaskStatus(projectId, taskStatusDto));
    }
}
