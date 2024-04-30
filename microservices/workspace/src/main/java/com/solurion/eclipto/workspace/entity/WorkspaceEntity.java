package com.solurion.eclipto.workspace.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ownerId")
    private Long ownerId;
}
