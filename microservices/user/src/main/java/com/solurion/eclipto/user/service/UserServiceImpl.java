package com.solurion.eclipto.user.service;

import com.solurion.eclipto.user.dto.UpdateUserRequest;
import com.solurion.eclipto.user.entity.UserEntity;
import com.solurion.eclipto.user.mapper.UpdateUserMapper;
import com.solurion.eclipto.user.repository.UserRepository;
import com.solurion.eclipto.user.entity.UserRole;
import com.solurion.eclipto.user.dto.RegisterRequest;
import com.solurion.eclipto.user.dto.UserInfoDto;
import com.solurion.eclipto.user.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UpdateUserMapper updateUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDto getUser(Long id) {
        return userMapper.toDto(
                userRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "User with same id not found"))
        );
    }

    @Override
    public UserDetails createUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with same email already exists");
        }
        return userRepository.save(
                userMapper.toEntity(registerRequest)
                        .toBuilder()
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .role(UserRole.ROLE_USER)
                        .build()
        );
    }

    @Transactional
    @Override
    public UserInfoDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "User with same id not found"));
        updateUserMapper.updateEntity(updateUserRequest, entity);
        return userMapper.toDto(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "User with same email not found"));
    }
}
