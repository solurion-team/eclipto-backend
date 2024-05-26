package com.solurion.eclipto.project.service;

import com.solurion.eclipto.common.jwt.JwtClaimsManager;
import com.solurion.eclipto.common.kafka.ProjectTopicConfig;
import com.solurion.eclipto.common.kafka.WorkspaceTopicConfig;
import com.solurion.eclipto.project.dto.CreateProjectRequest;
import com.solurion.eclipto.project.dto.ProjectAuthorityDto;
import com.solurion.eclipto.project.dto.ProjectInfoDto;
import com.solurion.eclipto.project.dto.UpdateProjectRequest;
import com.solurion.eclipto.project.entity.ProjectAuthorityEntity;
import com.solurion.eclipto.project.entity.ProjectEntity;
import com.solurion.eclipto.project.mapper.ProjectAuthorityMapper;
import com.solurion.eclipto.project.mapper.ProjectMapper;
import com.solurion.eclipto.project.mapper.UpdateProjectAuthorityMapper;
import com.solurion.eclipto.project.mapper.UpdateProjectMapper;
import com.solurion.eclipto.project.repository.ProjectAuthorityRepository;
import com.solurion.eclipto.project.repository.ProjectRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.solurion.eclipto.project.entity.ProjectAuthorityEntity.PrivilegeEnum.*;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UpdateProjectMapper updateProjectMapper;
    private final ProjectMapper projectMapper;
    private final KafkaTemplate<String, Long> kafkaTemplate;
    private final ProjectAuthorityMapper projectAuthorityMapper;
    private final ProjectAuthorityRepository projectAuthorityRepository;
    private final UpdateProjectAuthorityMapper updateProjectAuthorityMapper;
    private final JwtClaimsManager jwtClaimsManager;

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
        List<ProjectEntity> projects;
        if (workspaceId == null) {
            projects = new ArrayList<>();
            Long userId = jwtClaimsManager.extractUserId();
            for (ProjectAuthorityEntity projectAuthority : projectAuthorityRepository.findAllByUserId(userId)) {
                projectRepository.findById(projectAuthority.getProjectId()).ifPresent(projects::add);
            }
        } else {
            projects = projectRepository.findByWorkspaceId(workspaceId);
        }
        return projects.stream().map(projectMapper::toDto).toList();
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

    @Override
    public List<ProjectAuthorityDto> getProjectAuthorityEntity(Long projectId) {
        return projectAuthorityRepository.getAllByProjectId(projectId).stream()
                .map(projectAuthorityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectAuthorityDto createProjectAuthority(Long projectId, ProjectAuthorityDto projectAuthorityDto) {
        ProjectAuthorityEntity projectAuthorityEntity = projectAuthorityMapper.toEntity(projectAuthorityDto);
        projectAuthorityEntity.setProjectId(projectId);
        projectAuthorityRepository.save(projectAuthorityEntity);
        return projectAuthorityDto;
    }

    @Override
    @Transactional
    public ProjectAuthorityDto updateProjectAuthority(Long projectId, ProjectAuthorityDto projectAuthorityDto) {
        ProjectAuthorityEntity projectAuthorityEntity = projectAuthorityRepository.findByProjectIdAndUserId(
                projectId, projectAuthorityDto.getUserId()
        );
        updateProjectAuthorityMapper.updateEntity(projectAuthorityDto, projectAuthorityEntity);
        return projectAuthorityDto;
    }

    @Override
    @Transactional
    public void onUserDeleted(Long userId) {
        List<ProjectEntity> projectEntities = projectRepository.findAllByLeadId(userId);
        projectEntities.forEach(n -> {
            List<ProjectAuthorityEntity> entityList = projectAuthorityRepository.findAllByProjectId(n.getId());
            for (ProjectAuthorityEntity entity : entityList) {
                if (entity.getPrivilege() == WRITE) {
                    entity.setPrivilege(ADMIN);
                    n.setLeadId(entity.getUserId());
                    projectAuthorityRepository.save(entity);
                    projectRepository.save(n);
                    break;
                } else if (entity.getPrivilege() == READ) {
                    entity.setPrivilege(ADMIN);
                    n.setLeadId(entity.getUserId());
                    projectAuthorityRepository.save(entity);
                    projectRepository.save(n);
                    break;
                }
            }
            projectAuthorityRepository.deleteAllByUserId(userId);
        });
    }
}
