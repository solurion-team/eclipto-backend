package com.solurion.eclipto.task.service;

import com.solurion.eclipto.task.dto.*;

import java.util.List;

public interface TaskService {
    void deleteTask(Long taskId);

    List<TaskInfoDto> getAllTasks(Long projectId, Boolean isCompleted);

    List<TaskLiteDto> getLiteTasks(Long projectId);

    List<TaskStatusDto> getProjectTaskStatuses(Long projectId, Boolean includeTasks, Boolean isCompleted);

    TaskInfoDto getTask(Long taskId);

    TaskLiteDto postTask(CreateTaskRequest createTaskRequest);

    TaskStatusDto postTaskStatus(CreateTaskStatusRequest createTaskStatusRequest);

    TaskInfoDto updateTask(Long taskId, UpdateTaskRequest updateTaskRequest);

    TaskStatusDto updateTaskStatus(Long statusId, UpdateTaskStatusRequest taskStatusDto);
  
    void onUserDeleted(Long userId);
}
