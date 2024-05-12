package com.solurion.eclipto.task.service;

import com.solurion.eclipto.task.dto.*;
import com.solurion.eclipto.task.entity.TaskEntity;
import com.solurion.eclipto.task.entity.TaskStatusEntity;
import com.solurion.eclipto.task.mapper.TaskMapper;
import com.solurion.eclipto.task.mapper.TaskStatusMapper;
import com.solurion.eclipto.task.mapper.UpdateTaskMapper;
import com.solurion.eclipto.task.mapper.UpdateTaskStatusMapper;
import com.solurion.eclipto.task.repository.TaskRepository;
import com.solurion.eclipto.task.repository.TaskStatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TaskMapper taskMapper;
    private final TaskStatusMapper taskStatusMapper;
    private final UpdateTaskMapper updateTaskMapper;
    private final UpdateTaskStatusMapper updateTaskStatusMapper;

    @Override
    public void deleteTask(Long taskId) {
        if (taskRepository.existsById(taskId)){
            taskRepository.deleteById(taskId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<TaskInfoDto> getAllTasks(Long projectId) {
        return taskRepository.findAllByProjectId(projectId)
                .stream()
                .map(taskMapper::toTaskInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskLiteDto> getLiteTasks(Long projectId) {
        return taskRepository
                .findAllByProjectId(projectId)
                .stream()
                .map(taskMapper::toTaskLite)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TaskStatusDto> getProjectTaskStatuses(Long projectId, Boolean includeTasks) {
        List<TaskStatusEntity> entities = taskStatusRepository.findAllByProjectId(projectId);
        List<TaskStatusDto> statusDtoList = entities.stream().map(taskStatusMapper::toDto).toList();
        if(!includeTasks){
            statusDtoList.stream().forEach(obj -> obj.setTasks(null));
        }
        return statusDtoList;
    }

    @Override
    public TaskInfoDto getTask(Long taskId) {
        return taskMapper.toTaskInfo(taskRepository.findById(taskId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public TaskLiteDto postTask(CreateTaskRequest createTaskRequest) {
        return taskMapper.toTaskLite(taskRepository.save(taskMapper.toEntity(createTaskRequest)));
    }

    @Override
    public TaskStatusDto postTaskStatus(CreateTaskStatusRequest createTaskStatusRequest) {
        return taskStatusMapper
                .toDto(taskStatusRepository.save(taskStatusMapper.toEntity(createTaskStatusRequest)));
    }

    @Override
    @Transactional
    public TaskInfoDto updateTask(Long taskId,UpdateTaskRequest updateTaskRequest) {
        TaskEntity entity = taskRepository
                .findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateTaskMapper.updateEntity(updateTaskRequest, entity);
        if(updateTaskRequest.getStatusId() != null){
            entity.setStatus(taskStatusRepository
                    .findById(updateTaskRequest.getStatusId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        }
        return taskMapper.toTaskInfo(entity);
    }

    @Override
    @Transactional
    public TaskStatusDto updateTaskStatus(Long statusId, UpdateTaskStatusRequest updateTaskStatusRequest) {
        TaskStatusEntity entity = taskStatusRepository
                .findById(statusId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateTaskStatusMapper.updateTaskStatus(updateTaskStatusRequest, entity);
        return taskStatusMapper.toDto(entity);
    }
}
