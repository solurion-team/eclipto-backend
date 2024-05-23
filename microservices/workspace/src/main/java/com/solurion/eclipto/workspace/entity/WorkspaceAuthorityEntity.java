package com.solurion.eclipto.workspace.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "workspace_id")
    private Long workspaceId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "privilege")
    private PrivilegeEnum privilege;

    @RequiredArgsConstructor
    @Getter
    public enum PrivilegeEnum {
        READ("READ"),
        WRITE("WRITE"),
        ADMIN("ADMIN");
        private final String value;
    }
}
