package com.solurion.eclipto.workspace.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized

public class UpdateWorkspaceRequest {
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}
