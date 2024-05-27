package com.solurion.eclipto.workspace.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "workspace_authority")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceAuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "workspace_id", insertable = false, updatable = false)
    private Long workspaceId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "privilege")
    private Privilege privilege;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WorkspaceEntity workspace;

    public enum Privilege {
        READ,
        WRITE,
        ADMIN;
    }
}
