package com.solurion.eclipto.workspace.repository;


import com.solurion.eclipto.workspace.entity.WorkspaceAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkspaceAuthorityRepository extends JpaRepository<WorkspaceAuthorityEntity, Long> {
    List<WorkspaceAuthorityEntity> getAllByWorkspaceId(Long workspaceId);

    WorkspaceAuthorityEntity findByWorkspaceIdAndUserId(Long workspaceId, Long userId);

    List<WorkspaceAuthorityEntity> getAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);

    List<WorkspaceAuthorityEntity> findAllByWorkspaceId(Long workspaceId);
}