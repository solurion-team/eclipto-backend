package com.solurion.eclipto.workspace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@RequiredArgsConstructor
@Builder(toBuilder = true)
@Getter

public class WorkspaceInfoDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("owner_id")
    private Long ownerId;

}
