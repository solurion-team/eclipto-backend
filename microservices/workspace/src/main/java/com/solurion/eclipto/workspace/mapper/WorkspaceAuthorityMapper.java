package com.solurion.eclipto.workspace.mapper;

import com.solurion.eclipto.workspace.dto.WorkspaceAuthorityDto;
import com.solurion.eclipto.workspace.entity.WorkspaceAuthorityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceAuthorityMapper {
    WorkspaceAuthorityEntity toEntity(WorkspaceAuthorityDto dto);

    WorkspaceAuthorityDto toDto(WorkspaceAuthorityEntity entity);

}
