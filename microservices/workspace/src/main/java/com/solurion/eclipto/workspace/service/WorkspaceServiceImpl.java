package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.workspace.dto.CreateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.UpdateWorkspaceRequest;
import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.entity.WorkspaceEntity;
import com.solurion.eclipto.workspace.mapper.WorkspaceMapper;
import com.solurion.eclipto.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService{
    private final WorkspaceMapper workspaceMapper;
    private final WorkspaceRepository workspaceRepository;
    @Override
    public WorkspaceInfoDto getWorkspaceInfo(Long workspaceId) {
        return workspaceMapper
                .toDto(workspaceRepository
                    .findById(workspaceId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public void updateWorkspaceInfo(Long workspaceId,UpdateWorkspaceRequest updateWorkspaceRequest) {
        WorkspaceEntity workspaceEntity = workspaceRepository
                .findById(workspaceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (updateWorkspaceRequest.getDescription() != null){
            workspaceEntity.setDescription(updateWorkspaceRequest.getDescription());

        }
        if (updateWorkspaceRequest.getName() != null){
            workspaceEntity.setName(updateWorkspaceRequest.getName());
        }
        workspaceRepository.save(workspaceEntity);
    }

    @Override
    public void deleteWorkspace(Long workspaceId) {
        workspaceRepository.deleteById(workspaceId);
    }

    @Override
    public void createWorkspace(CreateWorkspaceRequest createWorkspaceRequest) {
        workspaceRepository.save(workspaceMapper.toEntity(createWorkspaceRequest));
    }
}
