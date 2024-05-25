package com.solurion.eclipto.workspace.repository;

import com.solurion.eclipto.workspace.entity.WorkspaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long> {
    List<WorkspaceEntity> findByOwnerId(Long ownerId);
}
