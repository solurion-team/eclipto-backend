package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.common.jwt.JwtClaimsManager;
import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.entity.WorkspaceEntity;
import com.solurion.eclipto.workspace.mapper.WorkspaceMapper;
import com.solurion.eclipto.workspace.repository.WorkspaceRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final JwtClaimsManager jwtClaimsManager;

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
        WorkspaceInfoDto workspaceInfoDto = workspaceMapper.toDto(workspaceEntity);
        workspaceEntity.setOwnerId(jwtClaimsManager.extractUserId());
        workspaceRepository.save(workspaceEntity);
        return workspaceInfoDto;
    }

    @Override
    public List<WorkspaceInfoDto> getWorkspaces() {
        List<WorkspaceEntity> workspaceEntities = workspaceRepository.findByOwnerId(jwtClaimsManager.extractUserId());
        return workspaceMapper.entitiesToDtos(workspaceEntities);
    }
////        return workspaceRepository.findAll().stream().map(workspaceMapper::toDto).toList();
//        if (workspaceId == null) {
//            return workspaceRepository.findAll().stream()
//                    .map(workspaceMapper::toDto)
//                    .collect(Collectors.toList());
//        } else {
//            Optional<WorkspaceEntity> workspaceOpt = workspaceRepository.findById(workspaceId);
//            if (workspaceOpt.isEmpty()) {
//                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Workspace with ID " + workspaceId + " not found");
//            }
//            WorkspaceEntity workspace = workspaceOpt.get();
//            Long ownerId = workspace.getOwnerId();
//            List<WorkspaceEntity> workspaces = workspaceRepository.findByOwnerId(ownerId);
//            return workspaces.stream()
//                    .map(workspaceMapper::toDto)
//                    .collect(Collectors.toList());
//        }
//    }

}
