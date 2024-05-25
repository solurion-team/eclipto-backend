package com.solurion.eclipto.project.mapper;

import com.solurion.eclipto.project.dto.ProjectAuthorityDto;
import com.solurion.eclipto.project.entity.ProjectAuthorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UpdateProjectAuthorityMapper {
    void updateEntity(
            ProjectAuthorityDto projectAuthorityDto, @MappingTarget ProjectAuthorityEntity projectAuthorityEntity
    );
}
