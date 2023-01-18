package com.security.cryptography.api.controller.specification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Hash API", description = "Generates hash for plain text")
public interface HashApi {
    @Operation(summary = "Encrypt plain text", description = "Encrypt plain text with hash algorithm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Text was encrypted successfully.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),
            @ApiResponse(responseCode = "400", description = "Input data is invalid."),
            @ApiResponse(responseCode = "401", description = "Authentication is necessary."),
            @ApiResponse(responseCode = "500", description = "Server is down.")})
    @PostMapping(value = "/encrypt")
    ResponseEntity<String> encrypt(
            @Parameter(description="Information to be encrypted", required=true, schema=@Schema(implementation=String.class), example="password") final char[] PLAIN_TEXT);

    @Operation(summary = "Verify plain text against hash", description = "Verify if the plaint text generates the same hash")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plain text is the same as the hash.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),
            @ApiResponse(responseCode = "400", description = "Input data is invalid."),
            @ApiResponse(responseCode = "401", description = "Authentication is necessary."),
            @ApiResponse(responseCode = "500", description = "Server is down.")})
    @PostMapping(value = "/encrypt")
    ResponseEntity<Boolean> verify(
            @Parameter(description="Information to be verified", required=true, schema=@Schema(implementation=String.class), example="password") final char[] PLAIN_TEXT,
            @Parameter(description="Hash to be compared", required=true, schema=@Schema(implementation=String.class), example="p@ssw0rd!123") final String HASH);
}
