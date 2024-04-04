package com.solurion.eclipto.task.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateTaskRequest
 */

@JsonTypeName("updateTask_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class UpdateTaskRequest {

  private Long id;

  private String title;

  private String description;

  private Long statusId;

  /**
   * Importance of the task
   */
  public enum PriorityEnum {
    LOW("low"),
    
    MEDIUM("medium"),
    
    HIGH("high");

    private String value;

    PriorityEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PriorityEnum fromValue(String value) {
      for (PriorityEnum b : PriorityEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private PriorityEnum priority;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dueDate;

  private Long assignedUserId;

  private Long reporterUserId;

  public UpdateTaskRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UpdateTaskRequest(Long id) {
    this.id = id;
  }

  public UpdateTaskRequest id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * ID of the task
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "7438546582", description = "ID of the task", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UpdateTaskRequest title(String title) {
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

  public UpdateTaskRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A task description that provides more detailed information about the task
   * @return description
  */
  
  @Schema(name = "description", example = "Go to metanit and learn all the chapters about Java. You can learn spring in the official tutorial", description = "A task description that provides more detailed information about the task", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UpdateTaskRequest statusId(Long statusId) {
    this.statusId = statusId;
    return this;
  }

  /**
   * Id of the status
   * @return statusId
  */
  
  @Schema(name = "status_id", example = "457345348", description = "Id of the status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status_id")
  public Long getStatusId() {
    return statusId;
  }

  public void setStatusId(Long statusId) {
    this.statusId = statusId;
  }

  public UpdateTaskRequest priority(PriorityEnum priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Importance of the task
   * @return priority
  */
  
  @Schema(name = "priority", description = "Importance of the task", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("priority")
  public PriorityEnum getPriority() {
    return priority;
  }

  public void setPriority(PriorityEnum priority) {
    this.priority = priority;
  }

  public UpdateTaskRequest dueDate(OffsetDateTime dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**
   * Due timestamp with timezone (RFC 3339)
   * @return dueDate
  */
  @Valid 
  @Schema(name = "due_date", example = "2024-03-28T00:07:19+03:00", description = "Due timestamp with timezone (RFC 3339)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("due_date")
  public OffsetDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(OffsetDateTime dueDate) {
    this.dueDate = dueDate;
  }

  public UpdateTaskRequest assignedUserId(Long assignedUserId) {
    this.assignedUserId = assignedUserId;
    return this;
  }

  /**
   * ID of the user who was assigned to the task
   * @return assignedUserId
  */
  
  @Schema(name = "assigned_user_id", example = "23486357348", description = "ID of the user who was assigned to the task", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assigned_user_id")
  public Long getAssignedUserId() {
    return assignedUserId;
  }

  public void setAssignedUserId(Long assignedUserId) {
    this.assignedUserId = assignedUserId;
  }

  public UpdateTaskRequest reporterUserId(Long reporterUserId) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateTaskRequest updateTaskRequest = (UpdateTaskRequest) o;
    return Objects.equals(this.id, updateTaskRequest.id) &&
        Objects.equals(this.title, updateTaskRequest.title) &&
        Objects.equals(this.description, updateTaskRequest.description) &&
        Objects.equals(this.statusId, updateTaskRequest.statusId) &&
        Objects.equals(this.priority, updateTaskRequest.priority) &&
        Objects.equals(this.dueDate, updateTaskRequest.dueDate) &&
        Objects.equals(this.assignedUserId, updateTaskRequest.assignedUserId) &&
        Objects.equals(this.reporterUserId, updateTaskRequest.reporterUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, statusId, priority, dueDate, assignedUserId, reporterUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateTaskRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    statusId: ").append(toIndentedString(statusId)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    assignedUserId: ").append(toIndentedString(assignedUserId)).append("\n");
    sb.append("    reporterUserId: ").append(toIndentedString(reporterUserId)).append("\n");
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

