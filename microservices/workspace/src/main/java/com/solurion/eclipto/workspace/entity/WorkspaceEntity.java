package com.solurion.eclipto.workspace.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "workspaces")
public class WorkspaceEntity {
    @Id
    @SequenceGenerator(name = "workspace_seq", sequenceName = "workspace_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workspace_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "owner_id")
    private Long ownerId;
}
