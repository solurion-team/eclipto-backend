package com.solurion.eclipto.task.service;

import com.solurion.eclipto.task.dto.*;

import java.util.List;

public interface TaskService {
    TaskInfoDto getTaskInfo(Long projectId, Long taskId);

    void createTask(Long projectId, PostLiteTaskRequest postLiteTaskRequest);

    void updateTask(Long projectId, UpdateTaskRequest updateTaskRequest);

    List<TaskLiteDto> getLiteTasks(Long projectId);

    void createTaskStatus(Long projectId, PostTaskStatusRequest taskStatusRequest);

    void updateTaskStatus(Long projectId, TaskStatusDto taskRequest);

    void deleteTask(Long projectId, Long taskId);

    void deleteAllTask(Long projectId);

    List<TaskInfoDto> getFullTasks(Long projectId);
}
