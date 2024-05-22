package com.solurion.eclipto.project.service;

import com.solurion.eclipto.common.kafka.ProjectTopicConfig;
import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectRequest;
import com.solurion.eclipto.project.entity.ProjectEntity;
import com.solurion.eclipto.project.mapper.ProjectMapper;
import com.solurion.eclipto.project.mapper.UpdateProjectMapper;
import com.solurion.eclipto.project.repository.ProjectRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UpdateProjectMapper updateProjectMapper;
    private final ProjectMapper projectMapper;
    private final KafkaTemplate<String, Long> kafkaTemplate;

    public ProjectInfoDto getProject(Long id) {
        return projectMapper.toDto(projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Project not found"))
        );
    }

    @Transactional
    public ProjectInfoDto updateProjectInfo(UpdateProjectRequest updateProjectRequest, Long id) {
        ProjectEntity currentProjectInfo = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "There is no project with same ID"));
        updateProjectMapper.updateEntity(updateProjectRequest, currentProjectInfo);
        return projectMapper.toDto(currentProjectInfo);
    }

    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            kafkaTemplate.send(ProjectTopicConfig.TOPIC, ProjectTopicConfig.DELETE_PROJECT_KEY, id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Project not found");
        }
    }

    @Override
    public ProjectInfoDto createProject(CreateProjectRequest createProjectRequest) {
        ProjectEntity entity = projectRepository.save(projectMapper.toEntity(createProjectRequest));
        kafkaTemplate.send(ProjectTopicConfig.TOPIC, ProjectTopicConfig.CREATE_PROJECT_KEY, entity.getId());
        return projectMapper.toDto(entity);
    }

    @Override
    public List<ProjectInfoDto> getProjects(@Nullable Long workspaceId) {
        return projectRepository.findAll().stream().map(projectMapper::toDto).toList();
    }

    @Override
    public void onWorkspaceDeleted(Long workspaceId) {
        List<ProjectEntity> entities = projectRepository.getAllByWorkspaceId(workspaceId);
        entities.parallelStream()
                .forEach(obj -> {
                    kafkaTemplate.send(ProjectTopicConfig.TOPIC, ProjectTopicConfig.DELETE_PROJECT_KEY, obj.getId());
                    projectRepository.delete(obj);
                });
    }
}
