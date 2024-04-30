package com.solurion.eclipto.project.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@Jacksonized
public class ProjectInfoDto {
    @NotNull
    @JsonProperty("id")
    private final Long id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotNull
    @JsonProperty("lead_id")
    private Long leadId;

    public ProjectInfoDto(Long id, String name, String description, Long leadId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.leadId = leadId;
    }
}
