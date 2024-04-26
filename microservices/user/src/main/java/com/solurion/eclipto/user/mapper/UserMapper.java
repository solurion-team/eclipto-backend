package com.solurion.eclipto.user.mapper;

import com.solurion.eclipto.user.dto.UpdateUserRequest;
import com.solurion.eclipto.user.entity.UserEntity;
import com.solurion.eclipto.user.dto.RegisterRequest;
import com.solurion.eclipto.user.dto.UserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserInfoDto toDto(UserEntity entity);
    UserEntity toEntity(UserInfoDto dto);
    UserEntity toEntity(RegisterRequest registerRequest);
}
