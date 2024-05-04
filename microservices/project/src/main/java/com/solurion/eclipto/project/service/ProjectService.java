package com.solurion.eclipto.project.service;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectRequest;

public interface ProjectService {
    ProjectInfoDto getProject(Long id);

    void updateProjectInfo(UpdateProjectRequest updateProjectRequest, Long id);

    void deleteProject(Long id);

    ProjectInfoDto createProject(CreateProjectRequest createProjectRequest, Long workspaceId);

}
