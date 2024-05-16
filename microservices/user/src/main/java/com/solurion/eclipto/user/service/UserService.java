package com.solurion.eclipto.user.service;

import com.solurion.eclipto.user.dto.RegisterRequest;
import com.solurion.eclipto.user.dto.UpdateUserRequest;
import com.solurion.eclipto.user.dto.UserInfoDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserInfoDto getUser(Long id);
    UserDetails createUser(RegisterRequest registerRequest);
    UserInfoDto updateUser(Long id, UpdateUserRequest updateUserRequest);
    void deleteUser(Long userId);
}
