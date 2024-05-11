package com.solurion.eclipto.task.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateTaskStatusRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class UpdateTaskStatusRequest {

  private String name;

  private String tint;

  public UpdateTaskStatusRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Task status name
   * @return name
  */
  
  @Schema(name = "name", example = "In progress", description = "Task status name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UpdateTaskStatusRequest tint(String tint) {
    this.tint = tint;
    return this;
  }

  /**
   * A color that is convenient for the user to identify the status
   * @return tint
  */
  @Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$") 
  @Schema(name = "tint", description = "A color that is convenient for the user to identify the status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tint")
  public String getTint() {
    return tint;
  }

  public void setTint(String tint) {
    this.tint = tint;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateTaskStatusRequest updateTaskStatusRequest = (UpdateTaskStatusRequest) o;
    return Objects.equals(this.name, updateTaskStatusRequest.name) &&
        Objects.equals(this.tint, updateTaskStatusRequest.tint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, tint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateTaskStatusRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    tint: ").append(toIndentedString(tint)).append("\n");
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

