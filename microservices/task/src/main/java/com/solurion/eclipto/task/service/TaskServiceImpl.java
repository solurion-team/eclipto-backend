package com.solurion.eclipto.task.service;

import com.solurion.eclipto.task.dto.*;
import com.solurion.eclipto.task.entity.TaskEntity;
import com.solurion.eclipto.task.entity.TaskStatusEntity;
import com.solurion.eclipto.task.mapper.TaskMapper;
import com.solurion.eclipto.task.mapper.TaskStatusMapper;
import com.solurion.eclipto.task.repository.TaskRepository;
import com.solurion.eclipto.task.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public TaskInfoDto getTaskInfo(Integer projectId, Integer taskId) {
        return TaskMapper.TASK_MAPPER.toTaskInfoDto(taskRepository.findById(taskId).orElseThrow());
    }

    @Override
    public Void createTask(Integer projectId, PostLiteTaskRequest postLiteTaskRequest) {
        TaskEntity task = TaskMapper.TASK_MAPPER.toTaskEntity(postLiteTaskRequest);
        task.setProjectId(projectId);
        taskRepository.save(task);
        return null;
    }

    @Override
    public Void updateTask(Integer projectId, UpdateTaskRequest updateTaskRequest) {
        return null;
    }

    @Override
    public List<TaskLiteDto> getLiteTasks(Integer projectId) {
        return taskRepository.findAllByProjectId(projectId)
                .stream()
                .map(TaskMapper.TASK_MAPPER::toTaskLiteDto)
                .collect(Collectors.toList());
    }

    @Override
    public Void createTaskStatus(Integer projectId, PostTaskStatusRequest taskStatusRequest) {
        TaskStatusEntity taskStatusEntity = TaskStatusMapper.TASK_STATUS_MAPPER.toEntity(taskStatusRequest);
        taskStatusEntity.setProjectId(projectId);
        taskStatusRepository.save(taskStatusEntity);
        return null;
    }

    @Override
    public Void updateTaskStatus(Integer projectId, TaskStatusDto taskStatusDto) {
        TaskStatusEntity taskStatusEntity = taskStatusRepository.findById(taskStatusDto.getId()).orElseThrow();
        taskStatusEntity.setName(taskStatusDto.getName());
        taskStatusEntity.setTint(taskStatusDto.getTint());
        taskStatusRepository.save(taskStatusEntity);
        return null;
    }
}
