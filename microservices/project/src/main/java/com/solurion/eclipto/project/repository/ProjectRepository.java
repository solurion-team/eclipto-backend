package com.solurion.eclipto.project.repository;

import com.solurion.eclipto.project.entity.ProjectAuthorityEntity;
import com.solurion.eclipto.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findByWorkspaceId(Long workspaceId);

    List<ProjectEntity> getAllByWorkspaceId(Long workspaceId);

    List<ProjectEntity> findAllByLeadId(Long userId);

    @Query("SELECT p FROM ProjectEntity p WHERE :userId MEMBER OF p.authorities")
    List<ProjectEntity> findAllByAuthoritiesUserId(Long userId);

    @Query("SELECT p FROM ProjectEntity p WHERE :userId MEMBER OF p.authorities AND p.workspaceId = :workspaceId")
    List<ProjectEntity> findAllByAuthoritiesUserIdAndWorkspaceId(Long userId, Long workspaceId);
}
