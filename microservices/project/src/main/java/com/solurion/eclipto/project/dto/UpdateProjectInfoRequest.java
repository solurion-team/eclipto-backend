package com.solurion.eclipto.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@Jacksonized
public class UpdateProjectInfoRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("lead_id")
    private Long leadId;
}
