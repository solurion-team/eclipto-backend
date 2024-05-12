package com.solurion.eclipto.project.dto;

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
 * Project information
 */

@Schema(name = "ProjectInfoDto", description = "Project information")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class ProjectInfoDto {

  private Long id;

  private String name;

  private String description;

  private String tint;

  private Long leadId;

  public ProjectInfoDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectInfoDto(Long id, String name, Long leadId) {
    this.id = id;
    this.name = name;
    this.leadId = leadId;
  }

  public ProjectInfoDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * ID of the project
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "7438546582", description = "ID of the project", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProjectInfoDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Project name, which identifies the project to the user
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "eclipto-backend", description = "Project name, which identifies the project to the user", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProjectInfoDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A project description that provides more detailed information about the project
   * @return description
  */
  
  @Schema(name = "description", example = "Project about microservice based eclipto backend project, that implement api-gateway pattern and ...", description = "A project description that provides more detailed information about the project", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProjectInfoDto tint(String tint) {
    this.tint = tint;
    return this;
  }

  /**
   * A color that is convenient for the user to identify the project
   * @return tint
  */
  @Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$") 
  @Schema(name = "tint", description = "A color that is convenient for the user to identify the project", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tint")
  public String getTint() {
    return tint;
  }

  public void setTint(String tint) {
    this.tint = tint;
  }

  public ProjectInfoDto leadId(Long leadId) {
    this.leadId = leadId;
    return this;
  }

  /**
   * Project lead user id
   * @return leadId
  */
  @NotNull 
  @Schema(name = "lead_id", example = "3757385734", description = "Project lead user id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("lead_id")
  public Long getLeadId() {
    return leadId;
  }

  public void setLeadId(Long leadId) {
    this.leadId = leadId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectInfoDto projectInfoDto = (ProjectInfoDto) o;
    return Objects.equals(this.id, projectInfoDto.id) &&
        Objects.equals(this.name, projectInfoDto.name) &&
        Objects.equals(this.description, projectInfoDto.description) &&
        Objects.equals(this.tint, projectInfoDto.tint) &&
        Objects.equals(this.leadId, projectInfoDto.leadId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, tint, leadId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectInfoDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    tint: ").append(toIndentedString(tint)).append("\n");
    sb.append("    leadId: ").append(toIndentedString(leadId)).append("\n");
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

