package com.solurion.eclipto.task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "index")
    private Integer index;
  
    @Column(name = "priority")
    private PriorityEnum priority;

    @Column(name = "due_date")
    private OffsetDateTime dueDate;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "assigned_user_id")
    private Long assignedUserId;

    @Column(name = "reporter_user_id")
    private Long reporterUserId;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TaskStatusEntity status;

    @Getter
    @RequiredArgsConstructor
    public enum PriorityEnum {
        LOW("low"),
        MEDIUM("medium"),
        HIGH("high");
        private final String value;
    }
}