package com.solurion.eclipto.task.repository;

import com.solurion.eclipto.task.entity.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity, Long> {
    List<TaskStatusEntity> findAllByProjectId(Long projectId);
}
