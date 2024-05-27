package com.solurion.eclipto.project.service;

import com.solurion.eclipto.project.dto.*;
import jakarta.annotation.Nullable;

import java.util.List;

public interface ProjectService {
    ProjectInfoDto getProject(Long id);

    ProjectInfoDto updateProjectInfo(UpdateProjectRequest updateProjectRequest, Long id);

    void deleteProject(Long id);

    ProjectInfoDto createProject(CreateProjectRequest createProjectRequest);

    List<ProjectInfoDto> getProjects(@Nullable Long workspaceId);

    void onWorkspaceDeleted(Long workspaceId);

    List<ProjectAuthorityDto> getProjectAuthorityEntity(Long projectId);

    ProjectAuthorityDto createProjectAuthority(Long projectId, ProjectAuthorityDto projectAuthorityDto);

    ProjectAuthorityDto updateProjectAuthority(Long projectId, ProjectAuthorityDto projectAuthorityDto);

    void onUserDeleted(Long userId);

    List<UserInfoDto> getUsersByIds(Long projectId);
}
