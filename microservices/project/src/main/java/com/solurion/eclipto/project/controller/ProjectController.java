package com.solurion.eclipto.project.controller;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectInfoRequest;
import com.solurion.eclipto.project.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/workspaces/{workspaceId}/projects/{projectId}/info")
    ResponseEntity<ProjectInfoDto> getProjectInfo(@PathVariable String projectId, @PathVariable String workspaceId){
        Long id = Long.parseLong(projectId);
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PutMapping("/workspaces/{workspaceId}/projects/{projectId}/info")
    ResponseEntity<ProjectInfoDto> updateProjectInfo(@PathVariable String projectId,
                                                     @PathVariable String workspaceId,
                                                     @RequestBody UpdateProjectInfoRequest updateProjectInfoRequest){
        Long id = Long.parseLong(projectId);
        projectService.updateProjectInfo(updateProjectInfoRequest, id);
        return ResponseEntity.noContent().header(
                "X-Description",
                "Project info updated successfully").build();
    }

    @DeleteMapping("/workspaces/{workspaceId}/projects/{projectId}")
    ResponseEntity<ProjectInfoDto> deleteProject(@PathVariable String projectId, @PathVariable String workspaceId){
        Long id = Long.parseLong(projectId);
        projectService.deleteProject(id);
        return ResponseEntity.noContent().header(
                "X-Description",
                "The project has been deleted").build();
    }

    @PostMapping("/workspaces/{workspaceId}/projects")
    ResponseEntity<ProjectInfoDto> createProject(@PathVariable String workspaceId,
                                                 @RequestBody CreateProjectRequest createProjectRequest){
        projectService.createProject(createProjectRequest);
        return ResponseEntity.noContent().build();
    }

}
