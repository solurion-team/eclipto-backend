package com.solurion.eclipto.project.service;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectRequest;
import com.solurion.eclipto.project.entity.ProjectEntity;
import com.solurion.eclipto.project.mapper.ProjectMapper;
import com.solurion.eclipto.project.mapper.ProjectMapperImpl;
import com.solurion.eclipto.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public void updateProjectInfo(UpdateProjectRequest updateProjectRequest, Long id) {
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
    }

    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    @Override
    public void createProject(CreateProjectRequest createProjectRequest, Long workspaceId) {
        projectRepository.save(projectMapper.toEntity(createProjectRequest, workspaceId));
    }


}
