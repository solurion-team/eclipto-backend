package com.solurion.eclipto.workspace.repository;

import com.solurion.eclipto.workspace.entity.WorkspaceAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceAuthorityRepository extends JpaRepository<WorkspaceAuthorityEntity, Long> {
}
