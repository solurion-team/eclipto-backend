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
 * Status of the task (or &#39;tasks column&#39; for users)
 */

@Schema(name = "TaskStatusDto", description = "Status of the task (or 'tasks column' for users)")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class TaskStatusDto {

  private Long id;

  private String name;

  private String tint;

  public TaskStatusDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TaskStatusDto(Long id, String name, String tint) {
    this.id = id;
    this.name = name;
    this.tint = tint;
  }

  public TaskStatusDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * ID of the status
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "7438546582", description = "ID of the status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TaskStatusDto name(String name) {
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

  public TaskStatusDto tint(String tint) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskStatusDto taskStatusDto = (TaskStatusDto) o;
    return Objects.equals(this.id, taskStatusDto.id) &&
        Objects.equals(this.name, taskStatusDto.name) &&
        Objects.equals(this.tint, taskStatusDto.tint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, tint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskStatusDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

