package com.solurion.eclipto.workspace.service;

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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final WorkspaceAuthorityMapper workspaceAuthorityMapper;
    private final WorkspaceAuthorityRepository workspaceAuthorityRepository;
    private final UpdateWorkspaceAuthorityMapper updateWorkspaceAuthorityMapper;

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
        return workspaceMapper.toDto(workspaceRepository.save(workspaceMapper.toEntity(request)));
    }

    @Override
    public WorkspaceAuthorityDto createWorkspaceAuthority(Long workspaceId, WorkspaceAuthorityDto workspaceAuthorityDto) {
        WorkspaceAuthorityEntity workspaceAuthorityEntity = workspaceAuthorityMapper.toEntity(workspaceAuthorityDto);
        workspaceAuthorityEntity.setWorkspaceId(workspaceId);
        workspaceAuthorityRepository.save(workspaceAuthorityEntity);
        return workspaceAuthorityDto;
    }

    @Override
    public List<WorkspaceAuthorityDto> getWorkspaceAuthorityEntity(Long workspaceId) {
        return workspaceAuthorityRepository.getAllByWorkspaceId(workspaceId).stream()
                .map(workspaceAuthorityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkspaceInfoDto> getWorkspaces(@Nullable Long workspaceId) {
        return workspaceRepository.findAll().stream().map(workspaceMapper::toDto).toList();
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