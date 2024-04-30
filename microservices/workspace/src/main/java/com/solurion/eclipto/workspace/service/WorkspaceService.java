package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;

public interface WorkspaceService {
    WorkspaceInfoDto getWorkspaceInfo(Long workspaceId);

    void updateWorkspaceInfo(Long workspaceId,UpdateWorkspaceRequest updateWorkspaceRequest);

    void deleteWorkspace(Long workspaceId);

    void createWorkspace(CreateWorkspaceRequest createWorkspaceRequest);
}
