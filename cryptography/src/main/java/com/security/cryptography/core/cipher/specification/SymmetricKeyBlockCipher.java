package com.security.cryptography.core.cipher.specification;

import com.security.cryptography.domain.entity.SymmetricKeyBlockCipherData;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface SymmetricKeyBlockCipher {
    SymmetricKeyBlockCipherData encrypt(final String ALGORITHM, char[] input)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException;
    SecretKey generateKey() throws InvalidKeySpecException, NoSuchAlgorithmException;
    IvParameterSpec generateIv();
    IvParameterSpec generateIv(final char[] INITIALIZATION_VECTOR);
    void wipeArray(char[] secret);
    char[] decrypt(final String ALGORITHM, final SymmetricKeyBlockCipherData SYMMETRIC_KEY_BLOCK_CIPHER_DATA)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException;
}
