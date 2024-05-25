package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.common.jwt.JwtClaimsManager;
import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceAuthorityDto;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
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
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Workspace not found");
        }
    }

    @Override
    public WorkspaceInfoDto createWorkspace(CreateWorkspaceRequest request) {
        WorkspaceEntity workspaceEntity = workspaceMapper.toEntity(request);
        workspaceEntity.setOwnerId(jwtClaimsManager.extractUserId());
        workspaceRepository.save(workspaceEntity);
        return workspaceMapper.toDto(workspaceEntity);
    }

    @Override
    public WorkspaceAuthorityDto createWorkspaceAuthority(Long workspaceId, WorkspaceAuthorityDto workspaceAuthorityDto) {
        WorkspaceAuthorityEntity workspaceAuthorityEntity = workspaceAuthorityMapper.toEntity(workspaceAuthorityDto);
        workspaceAuthorityEntity.setWorkspaceId(workspaceId);
        workspaceAuthorityRepository.save(workspaceAuthorityEntity);
        return workspaceAuthorityDto;
    }

    public List<WorkspaceInfoDto> getWorkspaces() {
        List<WorkspaceAuthorityEntity> workspaceAuthorityEntities = workspaceAuthorityRepository.getAllByUserId(jwtClaimsManager.extractUserId());
//        List<WorkspaceEntity> workspaceEntities = workspaceRepository.findByOwnerId(jwtClaimsManager.extractUserId());
        List<WorkspaceEntity> workspaceEntities = workspaceAuthorityEntities.stream().map(workspaceAuthority -> workspaceRepository.findById(workspaceAuthority.getWorkspaceId())
                        .orElse(null))
                .filter(Objects::nonNull) // Отфильтровываем null значения, если не найдены WorkspaceEntity
                .collect(Collectors.toList());
        return workspaceMapper.entitiesToDtos(workspaceEntities);
    }

    @Override
    public void onUserDeleted(Long userId) {
        List<WorkspaceEntity> workspaceEntities = workspaceRepository.findAllByOwnerId(userId);
        workspaceEntities.forEach(n -> {
            kafkaTemplate.send(n.getId());
        });
    }


    @Override
    public List<WorkspaceAuthorityDto> getWorkspaceAuthorityEntity(Long workspaceId) {
        return workspaceAuthorityRepository.getAllByWorkspaceId(workspaceId).stream()
                .map(workspaceAuthorityMapper::toDto)
                .collect(Collectors.toList());
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