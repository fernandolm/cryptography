package com.security.cryptography.service.specification;

import com.security.cryptography.domain.dto.SymmetricKeyBlockCipherDataDto;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface SymmetricKeyBlockCipherService {
    SymmetricKeyBlockCipherDataDto encrypt(char[] input)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException;

    char[] decrypt(final SymmetricKeyBlockCipherDataDto SYMMETRIC_KEY_BLOCK_CIPHER_DATA_DTO)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException;
}
