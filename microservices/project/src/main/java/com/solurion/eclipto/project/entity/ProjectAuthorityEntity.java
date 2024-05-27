package com.solurion.eclipto.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "project_authority")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id", insertable = false, updatable = false)
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "privilege")
    private Privilege privilege;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectEntity project;

    public enum Privilege {
        READ,
        WRITE,
        ADMIN;
    }
}
