package com.security.cryptography.api.controller.specification;

import com.security.cryptography.domain.dto.SymmetricKeyBlockCipherDataDto;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Tag(name = "Symmetric Key Block Cipher API", description = "Encrypt and Decrypt plain text")
public interface SymmetricKeyBlockCipherApi {
    @Operation(summary = "Encrypt plain text", description = "Encrypt plain text with symmetric-key block cipher algorithm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Text was encrypted successfully.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),
            @ApiResponse(responseCode = "400", description = "Input data is invalid."),
            @ApiResponse(responseCode = "401", description = "Authentication is necessary."),
            @ApiResponse(responseCode = "500", description = "Server is down.")})
    @PostMapping(value = "/encrypt")
    ResponseEntity<SymmetricKeyBlockCipherDataDto> encrypt(
            @Parameter(description="Information to be encrypted",
                    required=true,
                    schema=@Schema(implementation=char[].class),
                    example="secretPassword123!")
            char[] plainText)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
                NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, InvalidKeyException;

    @Operation(summary = "Decrypt plain text", description = "Decrypt plain text with symmetric-key block cipher algorithm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Text was decrypted successfully.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),
            @ApiResponse(responseCode = "400", description = "Input data is invalid."),
            @ApiResponse(responseCode = "401", description = "Authentication is necessary."),
            @ApiResponse(responseCode = "500", description = "Server is down.")})
    @PostMapping(value = "/decrypt")
    ResponseEntity<char[]> decrypt(
            @Parameter(description="Information to be decrypted",
                    required=true,
                    schema=@Schema(implementation=SymmetricKeyBlockCipherDataDto.class),
                    example="{cipherText=1SRVwIoBO1m28xbEcjGqC2QOwibKFM3JB8XiWQn34vatreuXvb, initializationVector=E6LZGgBajHmfGpUw}")
            final SymmetricKeyBlockCipherDataDto SYMMETRIC_KEY_BLOCK_CIPHER_DATA_DTO)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
                NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, InvalidKeyException;
}
