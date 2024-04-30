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
 * Request schema to create a workspace
 */

@Schema(name = "CreateWorkspaceRequest", description = "Request schema to create a workspace")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class CreateWorkspaceRequest {

  private String name;

  private String description;

  public CreateWorkspaceRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateWorkspaceRequest(String name) {
    this.name = name;
  }

  public CreateWorkspaceRequest name(String name) {
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

  public CreateWorkspaceRequest description(String description) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateWorkspaceRequest createWorkspaceRequest = (CreateWorkspaceRequest) o;
    return Objects.equals(this.name, createWorkspaceRequest.name) &&
        Objects.equals(this.description, createWorkspaceRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateWorkspaceRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

