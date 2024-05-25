package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceAuthorityDto;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;

import java.util.List;

public interface WorkspaceService {
    WorkspaceInfoDto getWorkspace(Long id);

    WorkspaceInfoDto updateWorkspaceInfo(UpdateWorkspaceRequest request, Long workspaceId);

    void deleteWorkspace(Long workspaceId);

    WorkspaceInfoDto createWorkspace(CreateWorkspaceRequest request);


    WorkspaceAuthorityDto createWorkspaceAuthority(Long workspaceId, WorkspaceAuthorityDto workspaceAuthorityDto);

    List<WorkspaceAuthorityDto> getWorkspaceAuthorityEntity(Long workspaceId);


    WorkspaceAuthorityDto updateWorkspaceAuthority(Long workspaceId, WorkspaceAuthorityDto workspaceAuthorityDto);

    List<WorkspaceInfoDto> getWorkspaces();

    void onUserDeleted(Long userId);
}
