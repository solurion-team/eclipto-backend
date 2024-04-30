package com.solurion.eclipto.workspace.controller;

import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.service.WorkspaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WorkspaceController {

    WorkspaceService workspaceService;

    @GetMapping("/v1/workspaces/{workspaceId}")
    ResponseEntity<WorkspaceInfoDto> getWorkspaceInfo(@PathVariable Long workspaceId){
        return ResponseEntity.ok(workspaceService.getWorkspace(workspaceId));
    }


}
