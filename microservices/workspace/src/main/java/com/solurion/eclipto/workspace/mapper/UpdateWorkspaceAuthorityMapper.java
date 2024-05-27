package com.solurion.eclipto.workspace.mapper;

import com.solurion.eclipto.workspace.dto.WorkspaceAuthorityDto;
import com.solurion.eclipto.workspace.entity.WorkspaceAuthorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UpdateWorkspaceAuthorityMapper {
    void updateEntity(
            WorkspaceAuthorityDto workspaceAuthorityDto, @MappingTarget WorkspaceAuthorityEntity workspaceAuthorityEntity
    );
}
