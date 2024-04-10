package com.solurion.eclipto.task.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "tasks_statuses")
public class TaskStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "name")
    private String name;
    @Column(name = "color")
    private String tint;
}