package com.solurion.eclipto.project.controller;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectInfoRequest;
import com.solurion.eclipto.project.service.ProjectServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {
    private final ProjectServiceImpl projectServiceImpl;

    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    @GetMapping("/workspaces/{workspaceId}/projects/{projectId}/info")
    ResponseEntity<ProjectInfoDto> getProjectInfo(@PathVariable Long projectId, @PathVariable String workspaceId){
        return ResponseEntity.ok(projectServiceImpl.getProject(projectId));
    }

    @PutMapping("/workspaces/{workspaceId}/projects/{projectId}/info")
    ResponseEntity<ProjectInfoDto> updateProjectInfo(@PathVariable Long projectId,
                                                     @PathVariable String workspaceId,
                                                     @RequestBody UpdateProjectInfoRequest updateProjectInfoRequest){
        projectServiceImpl.updateProjectInfo(updateProjectInfoRequest, projectId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/workspaces/{workspaceId}/projects/{projectId}")
    ResponseEntity<ProjectInfoDto> deleteProject(@PathVariable Long projectId, @PathVariable String workspaceId){
        projectServiceImpl.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/workspaces/{workspaceId}/projects")
    ResponseEntity<ProjectInfoDto> createProject(@PathVariable String workspaceId,
                                                 @RequestBody CreateProjectRequest createProjectRequest){
        projectServiceImpl.createProject(createProjectRequest);
        return ResponseEntity.noContent().build();
    }

}
