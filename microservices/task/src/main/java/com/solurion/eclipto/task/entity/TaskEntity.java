package com.solurion.eclipto.task.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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
    private Integer id;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status_id")
    private Integer status;
    @Column(name = "priority")
    private String priority;
    @Column(name = "due_date")
    private OffsetDateTime dueDate;
    @Column(name = "assigned_user_id")
    private Long assignedUserId;
    @Column(name = "reporter_user_id")
    private Long reporterUserId;
    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;


}