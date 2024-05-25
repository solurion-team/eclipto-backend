package com.solurion.eclipto.task.repository;

import com.solurion.eclipto.task.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    boolean existsByProjectId(Long projectId);

    void deleteByProjectId(Long projectId);


}
