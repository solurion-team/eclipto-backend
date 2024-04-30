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
 * Request schema to update a workspace
 */

@Schema(name = "UpdateWorkspaceRequest", description = "Request schema to update a workspace")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class UpdateWorkspaceRequest {

  private String name;

  private String description;

  public UpdateWorkspaceRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Workspace name, which identifies the workspace to the user
   * @return name
  */
  
  @Schema(name = "name", example = "eclipto-backend", description = "Workspace name, which identifies the workspace to the user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UpdateWorkspaceRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A workspace description that provides more detailed information about the workspace
   * @return description
  */
  
  @Schema(name = "description", example = "Workspace about microservice based eclipto backend workspace, that implement api-gateway pattern and ...", description = "A workspace description that provides more detailed information about the workspace", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    UpdateWorkspaceRequest updateWorkspaceRequest = (UpdateWorkspaceRequest) o;
    return Objects.equals(this.name, updateWorkspaceRequest.name) &&
        Objects.equals(this.description, updateWorkspaceRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateWorkspaceRequest {\n");
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

