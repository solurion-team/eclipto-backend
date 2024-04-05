package com.solurion.eclipto.task.service;

import com.solurion.eclipto.task.dto.*;
import com.solurion.eclipto.task.entity.TaskEntity;
import com.solurion.eclipto.task.entity.TaskStatusEntity;
import com.solurion.eclipto.task.mapper.TaskMapper;
import com.solurion.eclipto.task.mapper.TaskStatusMapper;
import com.solurion.eclipto.task.repository.TaskRepository;
import com.solurion.eclipto.task.repository.TaskStatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TaskMapper taskMapper;
    private final TaskStatusMapper taskStatusMapper;

    @Override
    public TaskInfoDto getTaskInfo(Long projectId, Long taskId) {
        return taskMapper.toTaskInfoDto(taskRepository.findById(taskId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Task not found")
        ));
    }

    @Transactional
    @Override
    public void createTask(Long projectId, PostLiteTaskRequest postLiteTaskRequest) {
        if (taskStatusRepository.existsById(postLiteTaskRequest.getStatusId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task status not found");
        }
        TaskEntity task = taskMapper.toTaskEntity(postLiteTaskRequest);
        task.setProjectId(projectId);
        taskRepository.save(task);
    }

    @Transactional
    @Override
    public void updateTask(Long projectId, UpdateTaskRequest updateTaskRequest) {
        TaskEntity task = taskRepository.findById(updateTaskRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Task not found"));
        if (updateTaskRequest.getStatusId() != null) {
            if (taskStatusRepository.findById(updateTaskRequest.getStatusId()).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found task status");
            }
            task.setStatus(taskStatusRepository.findById(updateTaskRequest.getStatusId()).get());
            if (updateTaskRequest.getAssignedUserId() != null) {
                task.setAssignedUserId(updateTaskRequest.getAssignedUserId());
            }

        }
        if (updateTaskRequest.getTitle() != null) {
            task.setTitle(updateTaskRequest.getTitle());
        }
        if (updateTaskRequest.getDescription() != null) {
            task.setDescription(updateTaskRequest.getDescription());
        }
        if (updateTaskRequest.getPriority() != null) {
            task.setPriority(taskMapper.toEntityEnum(updateTaskRequest.getPriority()));
        }
        if (updateTaskRequest.getDueDate() != null) {
            task.setDueDate(updateTaskRequest.getDueDate());

        }
        if (updateTaskRequest.getReporterUserId() != null) {
            task.setReporterUserId(updateTaskRequest.getReporterUserId());
        }
        taskRepository.save(task);
    }

    @Override
    public List<TaskLiteDto> getLiteTasks(Long projectId) {
        return taskRepository.findAllByProjectId(projectId)
                .stream()
                .map(taskMapper::toTaskLiteDto)
                .toList();
    }

    @Override
    public void createTaskStatus(Long projectId, PostTaskStatusRequest taskStatusRequest) {
        TaskStatusEntity taskStatusEntity = taskStatusMapper.toEntity(taskStatusRequest);
        taskStatusEntity.setProjectId(projectId);
        taskStatusRepository.save(taskStatusEntity);
    }

    @Override
    @Transactional
    public void updateTaskStatus(Long projectId, TaskStatusDto taskStatusDto) {
        TaskStatusEntity taskStatusEntity = taskStatusRepository.
                findById(taskStatusDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Task Status not found"));
        if (taskStatusDto.getName() != null) {
            taskStatusEntity.setName(taskStatusDto.getName());
        }
        if (taskStatusDto.getTint() != null) {
            taskStatusEntity.setTint(taskStatusDto.getTint());
        }
        taskStatusRepository.save(taskStatusEntity);
    }
}
