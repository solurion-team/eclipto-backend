package com.solurion.eclipto.task.mapper;

import com.solurion.eclipto.task.dto.PostTaskStatusRequest;
import com.solurion.eclipto.task.dto.TaskStatusDto;
import com.solurion.eclipto.task.entity.TaskStatusEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;



@Mapper(componentModel = "spring")
public interface TaskStatusMapper {
    TaskStatusEntity toEntity(PostTaskStatusRequest postTaskStatusRequest);
    TaskStatusDto toDto(TaskStatusEntity taskStatusEntity);
}
