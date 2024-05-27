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
 * User information
 */

@Schema(name = "UserInfoDto", description = "User information")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public class UserInfoDto {

  private Long id;

  private String firstName;

  private String lastName;

  private String iconTint;

  private String email;

  public UserInfoDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UserInfoDto(Long id, String firstName, String email) {
    this.id = id;
    this.firstName = firstName;
    this.email = email;
  }

  public UserInfoDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * ID of the user
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "7438546582", description = "ID of the user", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserInfoDto firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * User first name
   * @return firstName
  */
  @NotNull 
  @Schema(name = "first_name", example = "John", description = "User first name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UserInfoDto lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * User last name
   * @return lastName
  */
  
  @Schema(name = "last_name", example = "Lastname", description = "User last name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserInfoDto iconTint(String iconTint) {
    this.iconTint = iconTint;
    return this;
  }

  /**
   * A color that is convenient for the user
   * @return iconTint
  */
  @Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$") 
  @Schema(name = "icon_tint", description = "A color that is convenient for the user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("icon_tint")
  public String getIconTint() {
    return iconTint;
  }

  public void setIconTint(String iconTint) {
    this.iconTint = iconTint;
  }

  public UserInfoDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * User email
   * @return email
  */
  @NotNull 
  @Schema(name = "email", example = "myemail@mail.com", description = "User email", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInfoDto userInfoDto = (UserInfoDto) o;
    return Objects.equals(this.id, userInfoDto.id) &&
        Objects.equals(this.firstName, userInfoDto.firstName) &&
        Objects.equals(this.lastName, userInfoDto.lastName) &&
        Objects.equals(this.iconTint, userInfoDto.iconTint) &&
        Objects.equals(this.email, userInfoDto.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, iconTint, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserInfoDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    iconTint: ").append(toIndentedString(iconTint)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

