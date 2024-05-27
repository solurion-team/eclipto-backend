package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.common.jwt.JwtClaimsManager;
import com.solurion.eclipto.common.kafka.WorkspaceTopicConfig;
import com.solurion.eclipto.workspace.dto.*;
import com.solurion.eclipto.workspace.entity.WorkspaceAuthorityEntity;
import com.solurion.eclipto.workspace.entity.WorkspaceEntity;
import com.solurion.eclipto.workspace.mapper.UpdateWorkspaceAuthorityMapper;
import com.solurion.eclipto.workspace.mapper.WorkspaceAuthorityMapper;
import com.solurion.eclipto.workspace.mapper.WorkspaceMapper;
import com.solurion.eclipto.workspace.repository.WorkspaceAuthorityRepository;
import com.solurion.eclipto.workspace.repository.WorkspaceRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final WorkspaceAuthorityMapper workspaceAuthorityMapper;
    private final WorkspaceAuthorityRepository workspaceAuthorityRepository;
    private final UpdateWorkspaceAuthorityMapper updateWorkspaceAuthorityMapper;
    private final JwtClaimsManager jwtClaimsManager;
    private final KafkaTemplate<String, Long> kafkaTemplate;


    @Override
    public WorkspaceInfoDto getWorkspace(Long id) {
        return workspaceMapper.toDto(workspaceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "There is no workspace with same ID"))
        );
    }

    @Override
    @Transactional
    public WorkspaceInfoDto updateWorkspaceInfo(UpdateWorkspaceRequest request, Long workspaceId) {
        WorkspaceEntity currentRepositoryInfo = workspaceRepository.findById(workspaceId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN, "There is no workspace with same ID")
        );
        if (request.getName() != null) {
            currentRepositoryInfo.setName(request.getName());
        }
        if (request.getDescription() != null) {
            currentRepositoryInfo.setDescription(request.getDescription());
        }
        return workspaceMapper.toDto(currentRepositoryInfo);
    }

    @Override
    public void deleteWorkspace(Long workspaceId) {
        if (workspaceRepository.existsById(workspaceId)) {
            workspaceRepository.deleteById(workspaceId);
            kafkaTemplate.send(WorkspaceTopicConfig.TOPIC, WorkspaceTopicConfig.DELETE_WORKSPACE_KEY, workspaceId);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Workspace not found");
        }
    }

    @Override
    public WorkspaceInfoDto createWorkspace(CreateWorkspaceRequest request) {
        WorkspaceEntity workspaceEntity = workspaceMapper.toEntity(request, jwtClaimsManager.extractUserId());
        workspaceEntity = workspaceRepository.save(workspaceEntity);
        WorkspaceAuthorityDto workspaceAuthorityDto = new WorkspaceAuthorityDto(jwtClaimsManager.extractUserId(),
                WorkspaceAuthorityDto.PrivilegeEnum.ADMIN);
        createWorkspaceAuthority(workspaceEntity.getId(), workspaceAuthorityDto);
        return workspaceMapper.toDto(workspaceEntity);
    }

    @Override
    public WorkspaceAuthorityDto createWorkspaceAuthority(Long workspaceId, WorkspaceAuthorityDto workspaceAuthorityDto) {
        WorkspaceAuthorityEntity workspaceAuthorityEntity = workspaceAuthorityMapper.toEntity(workspaceAuthorityDto);
        workspaceAuthorityEntity.setWorkspaceId(workspaceId);
        workspaceAuthorityRepository.save(workspaceAuthorityEntity);
        return workspaceAuthorityDto;
    }

    @Transactional
    public List<WorkspaceInfoDto> getWorkspaces() {
        List<WorkspaceAuthorityEntity> workspaceAuthorityEntities = workspaceAuthorityRepository.getAllByUserId(jwtClaimsManager.extractUserId());
        List<Long> workspaceIds = workspaceAuthorityEntities
                .stream()
                .map(WorkspaceAuthorityEntity::getWorkspaceId)
                .toList();
        return workspaceRepository.findAllById(workspaceIds)
                .stream()
                .map(workspaceMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void onUserDeleted(Long userId) {
        List<WorkspaceEntity> workspaceEntities = workspaceRepository.findAllByOwnerId(userId);
        List<Long> workspaceIds = workspaceEntities
                .stream()
                .map(WorkspaceEntity::getId)
                .toList();
        workspaceRepository.deleteAllById(workspaceIds);
        workspaceAuthorityRepository.deleteAllByUserId(userId);

        workspaceEntities.forEach(n -> {
            kafkaTemplate.send(WorkspaceTopicConfig.TOPIC, WorkspaceTopicConfig.DELETE_WORKSPACE_KEY, n.getId());
        });
    }

    @Override
    public List<UserInfoDto> getUsersByIds(Long workspaceId) {
        List<WorkspaceAuthorityEntity> entityList = workspaceAuthorityRepository.findAllByWorkspaceId(workspaceId);
        List<Long> userIdList = entityList.stream()
                .map(WorkspaceAuthorityEntity::getUserId).toList();

        //TODO

        return null;
    }


    @Override
    public List<WorkspaceAuthorityDto> getWorkspaceAuthorityEntity(Long workspaceId) {
        return workspaceAuthorityRepository.getAllByWorkspaceId(workspaceId).stream()
                .map(workspaceAuthorityMapper::toDto)
                .toList();
    }


    @Override
    @Transactional
    public WorkspaceAuthorityDto updateWorkspaceAuthority(Long workspaceId, WorkspaceAuthorityDto workspaceAuthorityDto) {
        WorkspaceAuthorityEntity workspaceAuthorityEntity = workspaceAuthorityRepository.findByWorkspaceIdAndUserId(
                workspaceId, workspaceAuthorityDto.getUserId()
        );
        updateWorkspaceAuthorityMapper.updateEntity(workspaceAuthorityDto, workspaceAuthorityEntity);
        return workspaceAuthorityDto;
    }
}