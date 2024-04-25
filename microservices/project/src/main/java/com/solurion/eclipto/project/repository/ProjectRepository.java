package com.solurion.eclipto.project.repository;

import com.solurion.eclipto.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
