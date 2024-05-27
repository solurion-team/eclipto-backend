package com.solurion.eclipto.task.repository;

import com.solurion.eclipto.task.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    void deleteByProjectId(Long projectId);

    Optional<BoardEntity> findByProjectId(Long projectId);
}
