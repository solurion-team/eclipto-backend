package com.solurion.eclipto.project.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectInfoDto {
    @JsonProperty("id")
    private final Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("leadId")
    private Long leadId;

    public ProjectInfoDto(Long id, String name, String description, Long leadId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.leadId = leadId;
    }
}
