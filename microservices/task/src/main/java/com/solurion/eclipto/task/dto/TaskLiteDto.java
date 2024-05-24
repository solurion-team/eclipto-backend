package com.solurion.eclipto.task.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Minimum task information
 */

@Schema(name = "TaskLiteDto", description = "Minimum task information")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class TaskLiteDto {

  private Long id;

  private String title;

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

  private Long assignedUserId;

  private Boolean isCompleted;

  public TaskLiteDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TaskLiteDto(Long id, String title, PriorityEnum priority, Long assignedUserId) {
    this.id = id;
    this.title = title;
    this.priority = priority;
    this.assignedUserId = assignedUserId;
  }

  public TaskLiteDto id(Long id) {
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

  public TaskLiteDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Task title, which identifies the task to the user
   * @return title
  */
  @NotNull 
  @Schema(name = "title", example = "Learn Java core and Spring Boot", description = "Task title, which identifies the task to the user", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TaskLiteDto priority(PriorityEnum priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Importance of the task
   * @return priority
  */
  @NotNull 
  @Schema(name = "priority", description = "Importance of the task", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("priority")
  public PriorityEnum getPriority() {
    return priority;
  }

  public void setPriority(PriorityEnum priority) {
    this.priority = priority;
  }

  public TaskLiteDto assignedUserId(Long assignedUserId) {
    this.assignedUserId = assignedUserId;
    return this;
  }

  /**
   * ID of the user who was assigned to the task
   * @return assignedUserId
  */
  @NotNull 
  @Schema(name = "assigned_user_id", example = "23486357348", description = "ID of the user who was assigned to the task", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assigned_user_id")
  public Long getAssignedUserId() {
    return assignedUserId;
  }

  public void setAssignedUserId(Long assignedUserId) {
    this.assignedUserId = assignedUserId;
  }

  public TaskLiteDto isCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
    return this;
  }

  /**
   * Information about status of task
   * @return isCompleted
  */
  
  @Schema(name = "is_completed", example = "true", description = "Information about status of task", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_completed")
  public Boolean getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskLiteDto taskLiteDto = (TaskLiteDto) o;
    return Objects.equals(this.id, taskLiteDto.id) &&
        Objects.equals(this.title, taskLiteDto.title) &&
        Objects.equals(this.priority, taskLiteDto.priority) &&
        Objects.equals(this.assignedUserId, taskLiteDto.assignedUserId) &&
        Objects.equals(this.isCompleted, taskLiteDto.isCompleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, priority, assignedUserId, isCompleted);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskLiteDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    assignedUserId: ").append(toIndentedString(assignedUserId)).append("\n");
    sb.append("    isCompleted: ").append(toIndentedString(isCompleted)).append("\n");
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

