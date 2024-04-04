package com.solurion.eclipto.task.repository;

import com.solurion.eclipto.task.entity.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity, Long> {

}
