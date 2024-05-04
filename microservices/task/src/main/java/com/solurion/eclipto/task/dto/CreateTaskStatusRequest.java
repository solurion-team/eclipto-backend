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
 * CreateTaskStatusRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class CreateTaskStatusRequest {

  private String name;

  private String tint;

  private Long projectId;

  public CreateTaskStatusRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateTaskStatusRequest(String name, String tint, Long projectId) {
    this.name = name;
    this.tint = tint;
    this.projectId = projectId;
  }

  public CreateTaskStatusRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Task status name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "In progress", description = "Task status name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreateTaskStatusRequest tint(String tint) {
    this.tint = tint;
    return this;
  }

  /**
   * A color that is convenient for the user to identify the status
   * @return tint
  */
  @NotNull @Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$") 
  @Schema(name = "tint", description = "A color that is convenient for the user to identify the status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tint")
  public String getTint() {
    return tint;
  }

  public void setTint(String tint) {
    this.tint = tint;
  }

  public CreateTaskStatusRequest projectId(Long projectId) {
    this.projectId = projectId;
    return this;
  }

  /**
   * ID of the project
   * @return projectId
  */
  @NotNull 
  @Schema(name = "project_id", example = "23486357348", description = "ID of the project", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("project_id")
  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTaskStatusRequest createTaskStatusRequest = (CreateTaskStatusRequest) o;
    return Objects.equals(this.name, createTaskStatusRequest.name) &&
        Objects.equals(this.tint, createTaskStatusRequest.tint) &&
        Objects.equals(this.projectId, createTaskStatusRequest.projectId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, tint, projectId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateTaskStatusRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    tint: ").append(toIndentedString(tint)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
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

