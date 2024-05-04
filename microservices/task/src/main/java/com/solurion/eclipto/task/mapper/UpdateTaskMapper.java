package com.solurion.eclipto.task.mapper;

import com.solurion.eclipto.task.dto.UpdateTaskRequest;
import com.solurion.eclipto.task.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = TaskStatusMapper.class)
public interface UpdateTaskMapper {
    void updateEntity(UpdateTaskRequest updateTaskRequest, @MappingTarget TaskEntity taskEntity);

}
