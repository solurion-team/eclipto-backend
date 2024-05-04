package com.solurion.eclipto.project.service;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectRequest;
import com.solurion.eclipto.project.entity.ProjectEntity;
import com.solurion.eclipto.project.mapper.ProjectMapper;
import com.solurion.eclipto.project.mapper.ProjectMapperImpl;
import com.solurion.eclipto.project.repository.ProjectRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectInfoDto getProject(Long id) {
        return projectMapper.toDto(projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"))
        );
    }

    @Transactional
    public ProjectInfoDto updateProjectInfo(UpdateProjectRequest updateProjectRequest, Long id) {
        ProjectEntity currentProjectInfo = projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no project with same ID"));
        if (updateProjectRequest.getDescription() != null) {
            currentProjectInfo.setDescription(updateProjectRequest.getDescription());
        }
        if (updateProjectRequest.getName() != null) {
            currentProjectInfo.setName(updateProjectRequest.getName());
        }
        if (updateProjectRequest.getLeadId() != null) {
            currentProjectInfo.setLeadId(updateProjectRequest.getLeadId());
        }
        return projectMapper.toDto(currentProjectInfo);
    }

    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    @Override
    public ProjectInfoDto createProject(CreateProjectRequest createProjectRequest) {
        return projectMapper.toDto(projectRepository.save(projectMapper.toEntity(createProjectRequest)));
    }

    @Override
    public List<ProjectInfoDto> getProjects(@Nullable Long workspaceId) {
        return projectRepository.findAll().stream().map(projectMapper::toDto).toList();
    }
}
