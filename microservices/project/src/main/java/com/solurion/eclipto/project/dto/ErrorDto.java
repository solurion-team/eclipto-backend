package com.solurion.eclipto.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
public class ErrorDto extends Exception {
    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("error")
    private String error;

    @JsonProperty("message")
    private String message;

    @JsonProperty("path")
    private String path;
}