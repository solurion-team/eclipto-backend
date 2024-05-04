package com.solurion.eclipto.task.mapper;

import com.solurion.eclipto.task.dto.CreateTaskRequest;
import com.solurion.eclipto.task.dto.TaskInfoDto;
import com.solurion.eclipto.task.dto.TaskLiteDto;
import com.solurion.eclipto.task.dto.UpdateTaskRequest;
import com.solurion.eclipto.task.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(
        uses = TaskStatusMapper.class,
        componentModel = "spring"
)

public interface TaskMapper {
    TaskEntity toEntity(CreateTaskRequest createTaskRequest);

    TaskEntity toEntity(TaskInfoDto taskInfoDto);

    TaskEntity toEntity(UpdateTaskRequest updateTaskRequest);

    TaskEntity toEntity(TaskLiteDto taskLiteDto);

    TaskInfoDto toTaskInfo(TaskEntity taskEntity);

    TaskLiteDto toTaskLite(TaskEntity taskEntity);

}
