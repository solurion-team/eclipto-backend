package com.solurion.eclipto.user.api;

import com.solurion.eclipto.user.dto.*;
import com.solurion.eclipto.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<UserInfoDto> getUser(Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<List<UserInfoDto>> getUsersByIds(List<Long> ids) {
        return ResponseEntity.ok(userService.getUsersByIds(ids));
    }

    @Override
    public ResponseEntity<UserInfoDto> updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, updateUserRequest));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
