package com.solurion.eclipto.project.controller;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectRequest;
import com.solurion.eclipto.project.service.ProjectService;
import com.solurion.eclipto.project.service.ProjectServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/v1/projects/{projectId}")
    ResponseEntity<ProjectInfoDto> getProjectInfo(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }

    @PutMapping("/v1/projects/{projectId}")
    ResponseEntity<ProjectInfoDto> updateProjectInfo(@PathVariable Long projectId,
                                                     @RequestBody UpdateProjectRequest updateProjectRequest) {
        projectService.updateProjectInfo(updateProjectRequest, projectId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/v1/projects/{projectId}")
    ResponseEntity<ProjectInfoDto> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/v1/workspaces/{workspaceId}/projects")
    ResponseEntity<ProjectInfoDto> createProject(@PathVariable Long workspaceId,
                                                 @Validated @RequestBody CreateProjectRequest createProjectRequest) {
        return ResponseEntity.ok(projectService.createProject(createProjectRequest, workspaceId));
    }

}
