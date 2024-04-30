package com.solurion.eclipto.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@Jacksonized
public class ErrorDto extends Exception {
    @NotNull
    @JsonProperty("timestamp")
    private String timestamp;

    @NotNull
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("error")
    private String error;

    @JsonProperty("message")
    private String message;

    @NotNull
    @JsonProperty("path")
    private String path;
}
