package com.solurion.eclipto.workspace.mapper;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.entity.WorkspaceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {
    WorkspaceEntity toEntity(CreateWorkspaceRequest createWorkspaceRequest);

    WorkspaceEntity toEntity(UpdateWorkspaceRequest updateWorkspaceRequest);

    WorkspaceInfoDto toDto(WorkspaceEntity workspaceEntity);
}
