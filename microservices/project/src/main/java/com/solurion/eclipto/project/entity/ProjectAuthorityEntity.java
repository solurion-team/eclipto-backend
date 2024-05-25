package com.solurion.eclipto.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="project_authority")
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
    @Column(name = "project_id")
    private Long projectId;
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
