package com.solurion.eclipto.workspace.controller;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @GetMapping("/v1/workspaces/{workspaceId}")
    ResponseEntity<WorkspaceInfoDto> getWorkspaceInfo(@PathVariable Long workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspace(workspaceId));
    }

    @PutMapping("/v1/workspaces/{workspaceId}")
    ResponseEntity<UpdateWorkspaceRequest> updateWorkspaceInfo(@PathVariable Long workspaceId,
                                                               @RequestBody UpdateWorkspaceRequest request) {
        workspaceService.updateWorkspaceInfo(request, workspaceId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/v1/workspaces/{workspaceId}")
    ResponseEntity<WorkspaceInfoDto> deleteWorkspace(@PathVariable Long workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/v1/workspaces")
    ResponseEntity<CreateWorkspaceRequest> createWorkspace(@Validated @RequestBody CreateWorkspaceRequest request) {
        workspaceService.createWorkspace(request);
        return ResponseEntity.noContent().build();
    }

}
