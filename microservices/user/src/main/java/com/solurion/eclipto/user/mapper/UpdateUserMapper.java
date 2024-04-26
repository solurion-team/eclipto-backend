package com.solurion.eclipto.user.mapper;

import com.solurion.eclipto.user.dto.UpdateUserRequest;
import com.solurion.eclipto.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UpdateUserMapper {
    void updateEntity(UpdateUserRequest updateUserRequest, @MappingTarget UserEntity task);
}
