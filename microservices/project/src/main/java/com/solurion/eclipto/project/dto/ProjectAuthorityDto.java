package com.solurion.eclipto.project.dto;

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
 * Project Authority DTO
 */

@Schema(name = "ProjectAuthorityDto", description = "Project Authority DTO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class ProjectAuthorityDto {

  private Long userId;

  /**
   * Privilege
   */
  public enum PrivilegeEnum {
    READ("READ"),
    
    WRITE("WRITE"),
    
    ADMIN("ADMIN");

    private String value;

    PrivilegeEnum(String value) {
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
    public static PrivilegeEnum fromValue(String value) {
      for (PrivilegeEnum b : PrivilegeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private PrivilegeEnum privilege;

  public ProjectAuthorityDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectAuthorityDto(Long userId, PrivilegeEnum privilege) {
    this.userId = userId;
    this.privilege = privilege;
  }

  public ProjectAuthorityDto userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Id of user
   * @return userId
  */
  @NotNull 
  @Schema(name = "user_id", example = "3757385734", description = "Id of user", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("user_id")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public ProjectAuthorityDto privilege(PrivilegeEnum privilege) {
    this.privilege = privilege;
    return this;
  }

  /**
   * Privilege
   * @return privilege
  */
  @NotNull 
  @Schema(name = "privilege", description = "Privilege", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("privilege")
  public PrivilegeEnum getPrivilege() {
    return privilege;
  }

  public void setPrivilege(PrivilegeEnum privilege) {
    this.privilege = privilege;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectAuthorityDto projectAuthorityDto = (ProjectAuthorityDto) o;
    return Objects.equals(this.userId, projectAuthorityDto.userId) &&
        Objects.equals(this.privilege, projectAuthorityDto.privilege);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, privilege);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectAuthorityDto {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    privilege: ").append(toIndentedString(privilege)).append("\n");
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

