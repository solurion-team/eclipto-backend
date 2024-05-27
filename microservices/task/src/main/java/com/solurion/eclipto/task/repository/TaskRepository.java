package com.solurion.eclipto.task.repository;

import com.solurion.eclipto.task.entity.TaskEntity;
import com.solurion.eclipto.task.entity.TaskStatusEntity;
import org.h2.util.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("SELECT t FROM TaskEntity t WHERE t.board.projectId = :projectId")
    List<TaskEntity> findAllByProjectId(Long projectId);

    @Query("SELECT t FROM TaskEntity t WHERE t.assignedUserId = :userId OR t.reporterUserId = :userId")
    List<TaskEntity> findAllByAssignedUserIdOrReporterUserId(@Param("userId") Long userId);
}
