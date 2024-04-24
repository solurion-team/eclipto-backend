package com.solurion.eclipto.project.service;

import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectInfoRequest;
import com.solurion.eclipto.project.entity.ProjectInfoEntity;
import com.solurion.eclipto.project.mapper.ProjectInfoMapper;
import com.solurion.eclipto.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectInfoMapper projectInfoMapper;
    public ProjectInfoDto getProject(Long id){
        return projectInfoMapper.toDto(projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public void updateProjectInfo(UpdateProjectInfoRequest updateProjectInfoRequest, Long id) {
        ProjectInfoEntity currentProjectInfo = projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        currentProjectInfo.setDescription(updateProjectInfoRequest.getDescription());
        currentProjectInfo.setName(updateProjectInfoRequest.getName());
        currentProjectInfo.setLead_id(updateProjectInfoRequest.getLead_id());

    }

    public void deleteProject(Long id) {
        ProjectInfoEntity currentProject = projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        projectRepository.delete(currentProject);
    }

    public void createProject(CreateProjectRequest createProjectRequest) {
        ProjectInfoDto projectInfoDto = new ProjectInfoDto(
                null, createProjectRequest.getName(),
                null, createProjectRequest.getLead_id()
        );
        projectRepository.save(projectInfoMapper.toEntity(projectInfoDto));
    }
}
