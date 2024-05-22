package com.solurion.eclipto.project.repository;

import com.solurion.eclipto.project.entity.ProjectAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAuthorityRepository extends JpaRepository<ProjectAuthorityEntity, Long> {
}
