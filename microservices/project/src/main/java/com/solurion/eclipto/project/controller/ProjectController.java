package com.solurion.eclipto.project.controller;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectInfoRequest;
import com.solurion.eclipto.project.service.ProjectService;
import com.solurion.eclipto.project.service.ProjectServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/workspaces/{workspaceId}/projects/{projectId}/info")
    ResponseEntity<ProjectInfoDto> getProjectInfo(@PathVariable Long projectId, @PathVariable String workspaceId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }

    @PutMapping("/workspaces/{workspaceId}/projects/{projectId}/info")
    ResponseEntity<ProjectInfoDto> updateProjectInfo(@PathVariable Long projectId,
                                                     @PathVariable String workspaceId,
                                                     @RequestBody UpdateProjectInfoRequest updateProjectInfoRequest) {
        projectService.updateProjectInfo(updateProjectInfoRequest, projectId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/workspaces/{workspaceId}/projects/{projectId}")
    ResponseEntity<ProjectInfoDto> deleteProject(@PathVariable Long projectId, @PathVariable String workspaceId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/workspaces/{workspaceId}/projects")
    ResponseEntity<ProjectInfoDto> createProject(@PathVariable String workspaceId,
                                                 @RequestBody CreateProjectRequest createProjectRequest) {
        projectService.createProject(createProjectRequest);
        return ResponseEntity.noContent().build();
    }

}
