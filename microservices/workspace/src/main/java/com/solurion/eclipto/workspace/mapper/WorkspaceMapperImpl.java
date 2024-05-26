package com.solurion.eclipto.workspace.mapper;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.entity.WorkspaceEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkspaceMapperImpl implements WorkspaceMapper {

    @Override
    public WorkspaceEntity toEntity(WorkspaceInfoDto dto) {
        return WorkspaceEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .ownerId(dto.getOwnerId()).build();
    }

    @Override
    public WorkspaceInfoDto toDto(WorkspaceEntity entity) {
        return new WorkspaceInfoDto()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .ownerId(entity.getOwnerId());
    }

    @Override
    public WorkspaceEntity toEntity(CreateWorkspaceRequest request, Long ownerId){
        return WorkspaceEntity.builder()
                .id(null)
                .name(request.getName())
                .description(request.getDescription())
                .ownerId(null).build();
    }
}
