package com.solurion.eclipto.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CreateProjectRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("lead_id")
    private Long leadId;
}
