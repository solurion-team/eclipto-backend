package com.solurion.eclipto.workspace.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "workspaces")
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceEntity {
    @Id
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;
}
