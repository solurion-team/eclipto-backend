package com.solurion.eclipto.project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/v1")
public class ProjectController {
    @GetMapping("/workspaces/{workspaceId}/projects/{projectId}/info")
    public ResponseEntity<ProjectInfoDto> getProjectInfo(
            @PathVariable(value = "workspaceId", required = true) Integer workspaceId,
            @PathVariable(value = "projectId", required = true) Integer projectId
    ) {

    }
}
