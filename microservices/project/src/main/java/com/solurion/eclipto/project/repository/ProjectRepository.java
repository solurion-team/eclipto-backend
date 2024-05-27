package com.solurion.eclipto.project.repository;

import com.solurion.eclipto.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> getAllByWorkspaceId(Long workspaceId);
}
