package com.solurion.eclipto.workspace.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Error info
 */

@Schema(name = "ErrorDto", description = "Error info")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class ErrorDto {

  private Integer status;

  private String error;

  private String message;

  private String path;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  public ErrorDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ErrorDto(Integer status, String path, OffsetDateTime timestamp) {
    this.status = status;
    this.path = path;
    this.timestamp = timestamp;
  }

  public ErrorDto status(Integer status) {
    this.status = status;
    return this;
  }

  /**
   * HTTP response status code
   * @return status
  */
  @NotNull 
  @Schema(name = "status", example = "404", description = "HTTP response status code", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public ErrorDto error(String error) {
    this.error = error;
    return this;
  }

  /**
   * HTTP status error message
   * @return error
  */
  
  @Schema(name = "error", example = "Not Found", description = "HTTP status error message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("error")
  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public ErrorDto message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Error message
   * @return message
  */
  
  @Schema(name = "message", example = "Entity has not been found", description = "Error message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorDto path(String path) {
    this.path = path;
    return this;
  }

  /**
   * The failed request's path
   * @return path
  */
  @NotNull 
  @Schema(name = "path", example = "/notFoundPage", description = "The failed request's path", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("path")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public ErrorDto timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Error timestamp with timezone (RFC 3339)
   * @return timestamp
  */
  @NotNull @Valid 
  @Schema(name = "timestamp", example = "2022-06-17T10:55:19Z", description = "Error timestamp with timezone (RFC 3339)", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("timestamp")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDto errorDto = (ErrorDto) o;
    return Objects.equals(this.status, errorDto.status) &&
        Objects.equals(this.error, errorDto.error) &&
        Objects.equals(this.message, errorDto.message) &&
        Objects.equals(this.path, errorDto.path) &&
        Objects.equals(this.timestamp, errorDto.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, error, message, path, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDto {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

