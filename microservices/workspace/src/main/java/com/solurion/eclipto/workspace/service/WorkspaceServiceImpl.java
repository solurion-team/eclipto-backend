package com.solurion.eclipto.workspace.service;

import com.solurion.eclipto.workspace.dto.WorkspaceInfoDto;
import com.solurion.eclipto.workspace.mapper.WorkspaceMapper;
import com.solurion.eclipto.workspace.repository.WorkspaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    WorkspaceRepository workspaceRepository;
    WorkspaceMapper workspaceMapper;
    @Override
    public WorkspaceInfoDto getWorkspace(Long id) {
        return workspaceMapper.toDto(workspaceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There is no workspace with same ID"))
        );
    }
}
