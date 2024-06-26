package com.solurion.eclipto.task.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
    @Column(name = "tint")
    private String tint;
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;
}