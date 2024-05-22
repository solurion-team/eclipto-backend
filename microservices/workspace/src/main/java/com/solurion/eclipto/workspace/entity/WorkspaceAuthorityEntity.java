package com.solurion.eclipto.workspace.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class WorkspaceAuthorityEntity {
    private Long workspaceId;

    private Long userId;

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
