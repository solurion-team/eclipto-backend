package com.solurion.eclipto.workspace.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class CreateWorkspaceRequest {
    @NotNull
    @JsonProperty("name")
    String name;

    @JsonProperty("description")
    String description;
}
