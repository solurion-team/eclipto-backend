package com.solurion.eclipto.workspace.mapper;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.entity.WorkspaceEntity;

import java.util.List;

public interface WorkspaceMapper {
    WorkspaceEntity toEntity(WorkspaceInfoDto dto);

    WorkspaceInfoDto toDto(WorkspaceEntity entity);

    WorkspaceEntity toEntity(CreateWorkspaceRequest request);

    List<WorkspaceInfoDto> entitiesToDtos(List<WorkspaceEntity> entities);
}
