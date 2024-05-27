package com.solurion.eclipto.project.api;

import com.solurion.eclipto.project.dto.*;
import com.solurion.eclipto.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController implements ProjectApi {
    private final ProjectService projectService;

    @Override
    public ResponseEntity<ProjectAuthorityDto> createProjectAuthorities(Long projectId, ProjectAuthorityDto projectAuthorityDto) {
        return ResponseEntity.ok(projectService.createProjectAuthority(projectId, projectAuthorityDto));
    }

    @Override
    public ResponseEntity<ProjectInfoDto> deleteProject(Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProjectAuthorityDto>> getProjectAuthorities(Long projectId) {
        return ResponseEntity.ok(projectService.getProjectAuthorities(projectId));
    }

    @Override
    public ResponseEntity<ProjectInfoDto> getProjectInfo(Long projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }

    @Override
    public ResponseEntity<List<ProjectInfoDto>> getProjects(Long workspaceId) {
        return ResponseEntity.ok(projectService.getProjects(workspaceId));
    }

    @Override
    public ResponseEntity<ProjectInfoDto> postProject(CreateProjectRequest createProjectRequest) {
        return ResponseEntity.ok(projectService.createProject(createProjectRequest));
    }

    @Override
    public ResponseEntity<ProjectAuthorityDto> updateProjectAuthorities(Long projectId, ProjectAuthorityDto projectAuthorityDto) {
        return ResponseEntity.ok(projectService.updateProjectAuthority(projectId, projectAuthorityDto));
    }

    @Override
    public ResponseEntity<ProjectInfoDto> updateProjectInfo(Long projectId, UpdateProjectRequest updateProjectRequest) {
        return ResponseEntity.ok(projectService.updateProjectInfo(updateProjectRequest, projectId));
    }
}
