package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.entity.WorkspaceEntity;
import com.solurion.eclipto.workspace.mapper.WorkspaceMapper;
import com.solurion.eclipto.workspace.repository.WorkspaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public WorkspaceInfoDto getWorkspace(Long id) {
        return workspaceMapper.toDto(workspaceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There is no workspace with same ID"))
        );
    }

    @Override
    @Transactional
    public void updateWorkspaceInfo(UpdateWorkspaceRequest request, Long workspaceId) {
        WorkspaceEntity currentRepositoryInfo = workspaceRepository.findById(workspaceId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no workspace with same ID")
        );
        if (request.getName() != null) {
            currentRepositoryInfo.setName(request.getName());
        }
        if (request.getDescription() != null) {
            currentRepositoryInfo.setDescription(request.getDescription());
        }
    }

    @Override
    public void deleteWorkspace(Long workspaceId) {
        if (workspaceRepository.existsById(workspaceId)) {
            workspaceRepository.deleteById(workspaceId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workspace not found");
        }
    }

    @Override
    public void createWorkspace(CreateWorkspaceRequest request) {
        workspaceRepository.save(workspaceMapper.toEntity(request));
    }


}
