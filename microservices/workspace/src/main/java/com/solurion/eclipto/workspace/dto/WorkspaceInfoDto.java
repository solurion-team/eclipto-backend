package com.solurion.eclipto.workspace.dto;

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
 * Workspace information
 */

@Schema(name = "WorkspaceInfoDto", description = "Workspace information")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class WorkspaceInfoDto {

  private Long id;

  private String name;

  private String description;

  private Long ownerId;

  public WorkspaceInfoDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public WorkspaceInfoDto(Long id, String name, Long ownerId) {
    this.id = id;
    this.name = name;
    this.ownerId = ownerId;
  }

  public WorkspaceInfoDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * ID of the workspace
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "7438546582", description = "ID of the workspace", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public WorkspaceInfoDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Workspace name, which identifies the workspace to the user
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "eclipto-backend", description = "Workspace name, which identifies the workspace to the user", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WorkspaceInfoDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A workspace description that provides more detailed information about the workspace
   * @return description
  */
  
  @Schema(name = "description", example = "Workspace of solurion company", description = "A workspace description that provides more detailed information about the workspace", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public WorkspaceInfoDto ownerId(Long ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  /**
   * Workspace owner user id
   * @return ownerId
  */
  @NotNull 
  @Schema(name = "owner_id", example = "3757385734", description = "Workspace owner user id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("owner_id")
  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkspaceInfoDto workspaceInfoDto = (WorkspaceInfoDto) o;
    return Objects.equals(this.id, workspaceInfoDto.id) &&
        Objects.equals(this.name, workspaceInfoDto.name) &&
        Objects.equals(this.description, workspaceInfoDto.description) &&
        Objects.equals(this.ownerId, workspaceInfoDto.ownerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, ownerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkspaceInfoDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
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

