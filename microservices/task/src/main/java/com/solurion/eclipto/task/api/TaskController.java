package com.solurion.eclipto.task.api;

import com.solurion.eclipto.task.dto.*;
import com.solurion.eclipto.task.repository.BoardRepository;
import com.solurion.eclipto.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {
    private final TaskService taskService;

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<TaskInfoDto>> getAllTasks(Long projectId, Boolean isCompleted) {
        return ResponseEntity.ok(taskService.getAllTasks(projectId, isCompleted));
    }

    @Override
    public ResponseEntity<List<TaskLiteDto>> getLiteTasks(Long projectId) {
        return ResponseEntity.ok(taskService.getLiteTasks(projectId));
    }

    @Override
    public ResponseEntity<List<TaskStatusDto>> getProjectTaskStatuses(Long projectId, Boolean includeTasks, Boolean isCompleted) {
        if(includeTasks == null){
            includeTasks = false;
        }
        return ResponseEntity.ok(taskService.getProjectTaskStatuses(projectId, includeTasks, isCompleted));
    }

    @Override
    public ResponseEntity<TaskInfoDto> getTask(Long taskId) {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @Override
    public ResponseEntity<TaskLiteDto> postTask(CreateTaskRequest createTaskRequest) {
        return ResponseEntity.ok(taskService.postTask(createTaskRequest));
    }

    @Override
    public ResponseEntity<TaskStatusDto> postTaskStatus(CreateTaskStatusRequest createTaskStatusRequest) {
        return ResponseEntity.ok(taskService.postTaskStatus(createTaskStatusRequest));
    }

    @Override
    public ResponseEntity<TaskInfoDto> updateTask(Long taskId, UpdateTaskRequest updateTaskRequest) {
        return ResponseEntity.ok(taskService.updateTask(taskId, updateTaskRequest));
    }

    @Override
    public ResponseEntity<TaskStatusDto> updateTaskStatus(Long statusId, UpdateTaskStatusRequest updateTaskStatusRequest) {
        return ResponseEntity.ok(taskService.updateTaskStatus(statusId, updateTaskStatusRequest));
    }
}
