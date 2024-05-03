package com.solurion.eclipto.task.mapper;

import com.solurion.eclipto.task.dto.CreateTaskStatusRequest;
import com.solurion.eclipto.task.dto.TaskStatusDto;
import com.solurion.eclipto.task.entity.TaskStatusEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskStatusMapper {
    TaskStatusEntity toEntity(CreateTaskStatusRequest createTaskStatusRequest);

    TaskStatusDto toDto(TaskStatusEntity taskStatusEntity);
}
