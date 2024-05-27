package com.solurion.eclipto.task.service;

import com.solurion.eclipto.task.dto.*;
import com.solurion.eclipto.task.entity.BoardEntity;
import com.solurion.eclipto.task.entity.TaskEntity;
import com.solurion.eclipto.task.entity.TaskStatusEntity;
import com.solurion.eclipto.task.mapper.TaskMapper;
import com.solurion.eclipto.task.mapper.TaskStatusMapper;
import com.solurion.eclipto.task.mapper.UpdateTaskMapper;
import com.solurion.eclipto.task.mapper.UpdateTaskStatusMapper;
import com.solurion.eclipto.task.repository.BoardRepository;
import com.solurion.eclipto.task.repository.TaskRepository;
import com.solurion.eclipto.task.repository.TaskStatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
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
    private final BoardRepository boardRepository;

    @Override
    public void deleteTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<TaskInfoDto> getAllTasks(Long projectId, Boolean isCompleted) {
        List<TaskEntity> entities = taskRepository.findAllByProjectId(projectId);
        if(isCompleted!=null){
            entities = entities.stream().filter(task -> task.getIsCompleted() == isCompleted).toList();
        }
        entities.forEach(obj ->{
            TaskStatusEntity entity = obj.getStatus();
            entity.setTasks(null);
            obj.setStatus(entity);
        });
        return entities.stream().map(taskMapper::toTaskInfo).toList();
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
    public List<TaskStatusDto> getProjectTaskStatuses(Long projectId, Boolean includeTasks, Boolean isCompleted) {
        List<TaskStatusEntity> entities = taskStatusRepository.findAllByProjectId(projectId);
        if(!includeTasks){
            entities.forEach(obj -> obj.setTasks(null));
        } else if(isCompleted != null){
            entities.stream().forEach(obj -> {
                obj.setTasks(obj.getTasks().stream().filter(obj1 -> obj1.getIsCompleted()==isCompleted).toList());
            });
        }
        return entities.stream().map(taskStatusMapper::toDto).toList();
    }

    @Override
    public TaskInfoDto getTask(Long taskId) {
        TaskEntity entity = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.getStatus().setTasks(null);
        return taskMapper.toTaskInfo(entity);
    }

    @Override
    @Transactional
    public TaskLiteDto postTask(CreateTaskRequest createTaskRequest) {
        TaskEntity entity = taskRepository.save(taskMapper.toEntity(createTaskRequest));
        entity.setBoard(boardRepository.findByProjectId(createTaskRequest.getProjectId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setStatus(taskStatusRepository.findById(createTaskRequest.getStatusId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        entity.setIsCompleted(false);
        return taskMapper.toTaskLite(entity);
    }

    @Override
    @Transactional
    public TaskStatusDto postTaskStatus(CreateTaskStatusRequest createTaskStatusRequest) {
        TaskStatusEntity taskStatusEntity = taskStatusMapper.toEntity(createTaskStatusRequest);
        taskStatusEntity.setBoard(boardRepository.findByProjectId(createTaskStatusRequest.getProjectId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        taskStatusRepository.save(taskStatusEntity);
        return taskStatusMapper.toDto(taskStatusEntity);
    }

    @Override
    @Transactional
    public TaskInfoDto updateTask(Long taskId, UpdateTaskRequest updateTaskRequest) {
        TaskEntity entity = taskRepository
                .findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateTaskMapper.updateEntity(updateTaskRequest, entity);
        if (updateTaskRequest.getStatusId() != null) {
            entity.setStatus(taskStatusRepository
                    .findById(updateTaskRequest.getStatusId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        }
        entity.getStatus().setTasks(null);
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

    @Override
    @Transactional
    public void onUserDeleted(Long userId) {
        List<TaskEntity> entities = taskRepository.findAllByAssignedUserIdOrReporterUserId(userId);

        for (TaskEntity obj : entities) {
            if (Objects.equals(obj.getAssignedUserId(), userId)) {
                obj.setAssignedUserId(null);
            }
            if (Objects.equals(obj.getReporterUserId(), userId)) {
                obj.setReporterUserId(null);
            }
        }
        taskRepository.saveAll(entities);
    }

    @Override
    @Transactional
    public void onProjectCreated(Long projectId) {
        BoardEntity board = boardRepository.save(BoardEntity.builder()
                .projectId(projectId)
                .build());
        taskStatusRepository.save(
                TaskStatusEntity.builder()
                        .projectId(projectId)
                        .name("To do")
                        .tint("#42526E")
                        .board(board)
                        .build()
        );
        taskStatusRepository.save(
                TaskStatusEntity.builder()
                        .projectId(projectId)
                        .name("In progress")
                        .tint("#0052CC")
                        .board(board)
                        .build()
        );
        taskStatusRepository.save(
                TaskStatusEntity.builder()
                        .projectId(projectId)
                        .name("Completed")
                        .tint("#3cc962")
                        .board(board)
                        .build()
        );
    }

    @Override
    @Transactional
    public void onProjectDeleted(Long projectId) {
        boardRepository.deleteByProjectId(projectId);
    }
}
