package com.solurion.eclipto.task.mapper;

import com.solurion.eclipto.task.dto.TaskStatusDto;
import com.solurion.eclipto.task.dto.UpdateTaskStatusRequest;
import com.solurion.eclipto.task.entity.TaskStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateTaskStatusMapper {
    void updateTaskStatus(UpdateTaskStatusRequest updateTaskStatusRequest, @MappingTarget TaskStatusEntity taskStatusEntity);
}
