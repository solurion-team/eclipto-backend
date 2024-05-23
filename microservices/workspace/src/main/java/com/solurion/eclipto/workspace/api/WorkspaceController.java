package com.solurion.eclipto.workspace.api;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceAuthorityDto;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.service.WorkspaceService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkspaceController implements WorkspaceApi {

    private final WorkspaceService workspaceService;

    @Override
    public ResponseEntity<WorkspaceAuthorityDto> createWorkspaceAuthorities(Long workspaceId, WorkspaceAuthorityDto workspaceAuthorityDto) {
        return ResponseEntity.ok(workspaceService.createWorkspaceAuthority(workspaceId, workspaceAuthorityDto));
    }

    @Override
    public ResponseEntity<WorkspaceInfoDto> deleteWorkspace(Long workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<WorkspaceAuthorityDto>> getWorkspaceAuthorities(Long workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspaceAuthorityEntity(workspaceId));
    }

    @Override
    public ResponseEntity<WorkspaceInfoDto> getWorkspaceInfo(Long workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspace(workspaceId));
    }

    @Override
    public ResponseEntity<List<WorkspaceInfoDto>> getWorkspaces(Long workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspaces(workspaceId));
    }

    @Override
    public ResponseEntity<WorkspaceInfoDto> postWorkspace(CreateWorkspaceRequest createWorkspaceRequest) {
        return ResponseEntity.ok(workspaceService.createWorkspace(createWorkspaceRequest));
    }

    @Override
    public ResponseEntity<WorkspaceAuthorityDto> updateWorkspaceAuthorities(Long workspaceId, WorkspaceAuthorityDto workspaceAuthorityDto) {
        return ResponseEntity.ok(workspaceService.updateWorkspaceAuthority(workspaceId, workspaceAuthorityDto));
    }

    @Override
    public ResponseEntity<WorkspaceInfoDto> updateWorkspaceInfo(Long workspaceId,
                                                                UpdateWorkspaceRequest updateWorkspaceRequest) {
//        workspaceService.updateWorkspaceInfo(updateWorkspaceRequest, workspaceId);
        return ResponseEntity.ok(workspaceService.updateWorkspaceInfo(updateWorkspaceRequest, workspaceId));
    }
}
