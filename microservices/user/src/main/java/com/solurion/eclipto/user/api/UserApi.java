/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.solurion.eclipto.user.api;

import com.solurion.eclipto.user.dto.ErrorDto;
import com.solurion.eclipto.user.dto.UpdateUserRequest;
import com.solurion.eclipto.user.dto.UserInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
@Validated
@Tag(name = "user", description = "the user API")
public interface UserApi {

    /**
     * GET /v1/users/{userId}
     * Get user info
     *
     * @param userId ID of a user (required)
     * @return The user has been found (status code 200)
     *         or User not found (status code 403)
     *         or Unexpected error (status code 200)
     */
    @Operation(
        operationId = "getUser",
        description = "Get user info",
        tags = { "user" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The user has been found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfoDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "User not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "Unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerHttpAuthentication")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/v1/users/{userId}",
        produces = { "application/json" }
    )
    
    ResponseEntity<UserInfoDto> getUser(
        @Parameter(name = "userId", description = "ID of a user", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId
    );


    /**
     * PUT /v1/users/{userId}
     * Update user info
     *
     * @param userId ID of a user (required)
     * @param updateUserRequest Request to update a user (optional)
     * @return The user has been updated (status code 200)
     *         or User not found (status code 403)
     *         or Unexpected error (status code 200)
     */
    @Operation(
        operationId = "updateUser",
        description = "Update user info",
        tags = { "user" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The user has been updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfoDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "User not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "Unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerHttpAuthentication")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/v1/users/{userId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<UserInfoDto> updateUser(
        @Parameter(name = "userId", description = "ID of a user", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "UpdateUserRequest", description = "Request to update a user") @Valid @RequestBody(required = false) UpdateUserRequest updateUserRequest
    );

}