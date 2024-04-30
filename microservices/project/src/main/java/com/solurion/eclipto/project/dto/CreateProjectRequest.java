package com.solurion.eclipto.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
public class CreateProjectRequest {

    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotNull
    @JsonProperty("lead_id")
    private Long leadId;

}
