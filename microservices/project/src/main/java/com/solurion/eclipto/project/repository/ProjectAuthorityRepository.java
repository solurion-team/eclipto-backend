package com.solurion.eclipto.project.repository;

import com.solurion.eclipto.project.entity.ProjectAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectAuthorityRepository extends JpaRepository<ProjectAuthorityEntity, Long> {
    List<ProjectAuthorityEntity> getAllByProjectId(Long projectId);

    ProjectAuthorityEntity findByProjectIdAndUserId(Long projectId, Long userId);

    void deleteAllByUserId(Long userId);

    List<ProjectAuthorityEntity> findAllByUserId(Long userId);

    List<ProjectAuthorityEntity> findAllByProjectId(Long id);
}
