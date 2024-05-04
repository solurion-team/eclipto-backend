package com.solurion.eclipto.project.mapper;


import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapperImpl implements ProjectMapper {
    public ProjectInfoDto toDto(ProjectEntity entity) {
        return new ProjectInfoDto()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .leadId(entity.getLeadId());
    }

    public ProjectEntity toEntity(ProjectInfoDto dto) {
        return ProjectEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .leadId(dto.getLeadId())
                .build();
    }

    @Override
    public ProjectEntity toEntity(CreateProjectRequest createProjectRequest) {
        return ProjectEntity.builder()
                .id(null)
                .name(createProjectRequest.getName())
                .description(createProjectRequest.getDescription())
                .leadId(createProjectRequest.getLeadId())
                .workspaceId(createProjectRequest.getWorkspaceId())
                .build();
    }

//    public ProjectEntity toEntity(CreateProjectRequest createProjectRequest) {
//        return ProjectEntity.builder()
//                .id(null)
//                .name(createProjectRequest.getName())
//                .description(null)
//                .leadId(createProjectRequest.getLeadId())
//                .build();
//    }
}
