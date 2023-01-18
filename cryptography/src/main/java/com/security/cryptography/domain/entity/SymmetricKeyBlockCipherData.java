package com.security.cryptography.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class SymmetricKeyBlockCipherData {
    private char[] initializationVector;
    private String cipherText;
}
