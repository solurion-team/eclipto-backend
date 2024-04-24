package com.solurion.eclipto.project.mapper;


import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.entity.ProjectInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectInfoMapper {
    public ProjectInfoDto toDto(ProjectInfoEntity entity){
        return ProjectInfoDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .lead_id(entity.getLead_id())
                .build();
    }

    public ProjectInfoEntity toEntity(ProjectInfoDto dto){
        return ProjectInfoEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .lead_id(dto.getLead_id())
                .build();
    }

    public ProjectInfoEntity CreateProjectRequestToProjectInfoEntity(CreateProjectRequest createProjectRequest){
        return ProjectInfoEntity.builder()
                .id(null)
                .name(createProjectRequest.getName())
                .description(null)
                .lead_id(createProjectRequest.getLead_id())
                .build();
    }
}
