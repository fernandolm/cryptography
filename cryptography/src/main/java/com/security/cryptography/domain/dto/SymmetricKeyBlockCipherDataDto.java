package com.security.cryptography.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SymmetricKeyBlockCipherDataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4717418669181023528L;
    @Schema(name = "initializationVector", type = "char[]", description = "random initialization vector", example = "E6LZGgBajHmfGpUw")
    public char[] initializationVector;
    @Schema(name = "cipherText", type = "String", description = "Encrypted data", example = "1SRVwIoBO1m28xbEcjGqC2QOwibKFM3JB8XiWQn34vatreuXvb")
    public String cipherText;
}
