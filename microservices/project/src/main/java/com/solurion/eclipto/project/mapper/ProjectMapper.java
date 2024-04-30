package com.solurion.eclipto.project.mapper;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.entity.ProjectEntity;

public interface ProjectMapper {
    ProjectInfoDto toDto(ProjectEntity entity);

    ProjectEntity toEntity(ProjectInfoDto dto);

    ProjectEntity toEntity(CreateProjectRequest createProjectRequest, Long workspaceId);
}
