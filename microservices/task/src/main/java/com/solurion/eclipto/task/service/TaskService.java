package com.solurion.eclipto.task.service;

import com.solurion.eclipto.task.dto.*;

import java.util.List;

public interface TaskService {
    TaskInfoDto getTaskInfo(Integer projectId, Integer taskId);
    Void createTask(Integer projectId, PostLiteTaskRequest postLiteTaskRequest);
    Void updateTask(Integer projectId, UpdateTaskRequest updateTaskRequest);
    List<TaskLiteDto> getLiteTasks(Integer projectId);
    Void createTaskStatus(Integer projectId, PostTaskStatusRequest taskStatusRequest);
    Void updateTaskStatus(Integer projectId, TaskStatusDto taskRequest);
}
