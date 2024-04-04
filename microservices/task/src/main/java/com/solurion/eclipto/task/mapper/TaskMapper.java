package com.solurion.eclipto.task.mapper;

import com.solurion.eclipto.task.dto.PostLiteTaskRequest;
import com.solurion.eclipto.task.dto.TaskInfoDto;
import com.solurion.eclipto.task.dto.TaskLiteDto;
import com.solurion.eclipto.task.dto.UpdateTaskRequest;
import com.solurion.eclipto.task.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        uses = TaskStatusMapper.class,
        componentModel = "spring"
)
public interface TaskMapper {
    TaskInfoDto toTaskInfoDto(TaskEntity taskEntity);
    TaskEntity toTaskEntity(TaskInfoDto taskInfoDto);
    TaskEntity.PriorityEnum toEntityEnum(UpdateTaskRequest.PriorityEnum priorityEnum);
    TaskEntity toTaskEntity(PostLiteTaskRequest postLiteTaskRequest);
    TaskLiteDto toTaskLiteDto(TaskEntity taskEntity);

}
