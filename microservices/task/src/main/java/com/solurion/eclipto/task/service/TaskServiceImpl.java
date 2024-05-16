package com.solurion.eclipto.task.service;

import com.solurion.eclipto.common.kafka.UserTopicConfig;
import com.solurion.eclipto.common.kafka.UserTopicListener;
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
        if(includeTasks){
            statusDtoList
                    .stream()
                    .forEach(obj -> obj.setTasks(
                            taskRepository
                                    .findAllByStatus(taskStatusMapper.toEntity(obj))
                                    .stream()
                                    .map(taskMapper::toTaskLite)
                                    .toList()
                    ));
        }
        return statusDtoList;
    }

    @Override
    public TaskInfoDto getTask(Long taskId) {
        return taskMapper.toTaskInfo(taskRepository.findById(taskId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public TaskLiteDto postLiteTask(CreateTaskRequest createTaskRequest) {
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
        return taskMapper.toTaskInfo(entity);
    }

    @Override
    @Transactional
    public TaskStatusDto updateTaskStatus(Long statusId, TaskStatusDto taskStatusDto) {
        TaskStatusEntity entity = taskStatusRepository
                .findById(statusId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateTaskStatusMapper.updateTaskStatus(taskStatusDto, entity);
        return taskStatusMapper.toDto(entity);
    }

    @Override
    public void onUserDeleted(Long userId) {

    }
}
