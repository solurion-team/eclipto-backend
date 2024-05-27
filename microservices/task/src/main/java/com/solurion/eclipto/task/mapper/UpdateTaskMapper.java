package com.solurion.eclipto.task.mapper;

import com.solurion.eclipto.task.dto.UpdateTaskRequest;
import com.solurion.eclipto.task.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = TaskStatusMapper.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UpdateTaskMapper {
    void updateEntity(UpdateTaskRequest updateTaskRequest, @MappingTarget TaskEntity taskEntity);

}
