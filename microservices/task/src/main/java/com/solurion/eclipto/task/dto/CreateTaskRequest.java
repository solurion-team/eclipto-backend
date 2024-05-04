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
 * CreateTaskRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class CreateTaskRequest {

  private String title;

  private Long statusId;

  private Long reporterUserId;

  private Long projectId;

  public CreateTaskRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateTaskRequest(Long statusId, Long projectId) {
    this.statusId = statusId;
    this.projectId = projectId;
  }

  public CreateTaskRequest title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Task title, which identifies the task to the user
   * @return title
  */
  
  @Schema(name = "title", example = "Learn Java core and Spring Boot", description = "Task title, which identifies the task to the user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CreateTaskRequest statusId(Long statusId) {
    this.statusId = statusId;
    return this;
  }

  /**
   * Id of the status
   * @return statusId
  */
  @NotNull 
  @Schema(name = "status_id", example = "457345348", description = "Id of the status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status_id")
  public Long getStatusId() {
    return statusId;
  }

  public void setStatusId(Long statusId) {
    this.statusId = statusId;
  }

  public CreateTaskRequest reporterUserId(Long reporterUserId) {
    this.reporterUserId = reporterUserId;
    return this;
  }

  /**
   * ID of the user who reported the task
   * @return reporterUserId
  */
  
  @Schema(name = "reporter_user_id", example = "23486357348", description = "ID of the user who reported the task", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reporter_user_id")
  public Long getReporterUserId() {
    return reporterUserId;
  }

  public void setReporterUserId(Long reporterUserId) {
    this.reporterUserId = reporterUserId;
  }

  public CreateTaskRequest projectId(Long projectId) {
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
    CreateTaskRequest createTaskRequest = (CreateTaskRequest) o;
    return Objects.equals(this.title, createTaskRequest.title) &&
        Objects.equals(this.statusId, createTaskRequest.statusId) &&
        Objects.equals(this.reporterUserId, createTaskRequest.reporterUserId) &&
        Objects.equals(this.projectId, createTaskRequest.projectId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, statusId, reporterUserId, projectId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateTaskRequest {\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    statusId: ").append(toIndentedString(statusId)).append("\n");
    sb.append("    reporterUserId: ").append(toIndentedString(reporterUserId)).append("\n");
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

