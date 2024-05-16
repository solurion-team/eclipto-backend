package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;

public interface WorkspaceService {
    WorkspaceInfoDto getWorkspace(Long id);

    WorkspaceInfoDto updateWorkspaceInfo(UpdateWorkspaceRequest request, Long workspaceId);

    void deleteWorkspace(Long workspaceId);

    WorkspaceInfoDto createWorkspace(CreateWorkspaceRequest request);
}
