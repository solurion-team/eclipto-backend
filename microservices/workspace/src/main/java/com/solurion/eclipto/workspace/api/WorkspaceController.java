package com.solurion.eclipto.workspace.api;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WorkspaceController implements WorkspaceApi{

    private final WorkspaceService workspaceService;

    @DeleteMapping ("/v1/workspaces/{workspaceId}")
    @Override
    public ResponseEntity<WorkspaceInfoDto> deleteWorkspace(@PathVariable Long workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v1/workspaces/{workspaceId}")
    @Override
    public ResponseEntity<WorkspaceInfoDto> getWorkspaceInfo(@PathVariable Long workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspace(workspaceId));
    }

    @PostMapping("/v1/workspaces")
    @Override
    public ResponseEntity<WorkspaceInfoDto> postWorkspace(@Validated @RequestBody CreateWorkspaceRequest createWorkspaceRequest) {
        workspaceService.createWorkspace(createWorkspaceRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/v1/workspaces/{workspaceId}")
    @Override
    public ResponseEntity<WorkspaceInfoDto> updateWorkspaceInfo(@PathVariable Long workspaceId,
                                                                @RequestBody UpdateWorkspaceRequest updateWorkspaceRequest) {
        workspaceService.updateWorkspaceInfo(updateWorkspaceRequest, workspaceId);
        return ResponseEntity.noContent().build();
    }
}
