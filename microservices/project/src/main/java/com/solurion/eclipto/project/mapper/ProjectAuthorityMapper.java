package com.solurion.eclipto.project.mapper;

import com.solurion.eclipto.project.dto.ProjectAuthorityDto;
import com.solurion.eclipto.project.entity.ProjectAuthorityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectAuthorityMapper {

    ProjectAuthorityEntity toEntity(ProjectAuthorityDto projectAuthorityDto);

    ProjectAuthorityDto toDto(ProjectAuthorityEntity projectAuthorityEntity);
}
