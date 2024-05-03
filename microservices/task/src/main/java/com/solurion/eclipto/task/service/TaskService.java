package com.solurion.eclipto.task.service;

import com.solurion.eclipto.task.dto.*;

import java.util.List;

public interface TaskService {
    void deleteTask(Long taskId);

    List<TaskInfoDto> getAllTasks(Long projectId);

    List<TaskLiteDto> getLiteTasks(Long projectId);

    TaskStatusDto getProjectTaskStatuses(Long projectId, Boolean includeTasks);

    TaskInfoDto getTask(Long taskId);

    TaskLiteDto postLiteTask(CreateTaskRequest createTaskRequest);

    TaskStatusDto postTaskStatus(CreateTaskStatusRequest createTaskStatusRequest);

    TaskInfoDto updateTask(Long taskId, UpdateTaskRequest updateTaskRequest);

    TaskStatusDto updateTaskStatus(Long statusId, TaskStatusDto taskStatusDto);
}
