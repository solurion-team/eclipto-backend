package com.solurion.eclipto.project.service;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectRequest;
import jakarta.annotation.Nullable;

import java.util.List;

public interface ProjectService {
    ProjectInfoDto getProject(Long id);

    ProjectInfoDto updateProjectInfo(UpdateProjectRequest updateProjectRequest, Long id);

    void deleteProject(Long id);

    ProjectInfoDto createProject(CreateProjectRequest createProjectRequest);

    List<ProjectInfoDto> getProjects(@Nullable Long workspaceId);

    void onWorkspaceDeleted(Long workspaceId);
}
