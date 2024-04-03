package com.solurion.eclipto.task.mapper;

import com.solurion.eclipto.task.dto.PostLiteTaskRequest;
import com.solurion.eclipto.task.dto.TaskInfoDto;
import com.solurion.eclipto.task.dto.TaskLiteDto;
import com.solurion.eclipto.task.entity.TaskEntity;
import com.solurion.eclipto.task.mapper.helpers.HelperMapper;
import com.solurion.eclipto.task.repository.TaskStatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class TaskMapper {
    public static final TaskMapper TASK_MAPPER = Mappers.getMapper(TaskMapper.class);
    @Autowired
    protected HelperMapper helperMapper;

    @Mapping(source = "statusId",target = "status")
    public abstract TaskEntity toTaskEntity(PostLiteTaskRequest postLiteTaskRequest);
    public abstract TaskLiteDto toTaskLiteDto(TaskEntity taskEntity);
    @Mapping(target = "status", expression = "java(helperMapper.getStatusDto(taskEntity.getStatus()))")
    public abstract TaskInfoDto toTaskInfoDto(TaskEntity taskEntity);

}
