package com.solurion.eclipto.task.mapper.helpers;

import com.solurion.eclipto.task.dto.TaskStatusDto;
import com.solurion.eclipto.task.mapper.TaskStatusMapper;
import com.solurion.eclipto.task.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelperMapper {
    private final TaskStatusRepository taskStatusRepository;
    public TaskStatusDto getStatusDto(Integer id){
        return TaskStatusMapper.TASK_STATUS_MAPPER.toDto(taskStatusRepository.findById(id).orElseThrow());
    }
}
