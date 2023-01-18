package com.security.cryptography.core.cipher;

import com.security.cryptography.core.cipher.specification.SymmetricKeyBlockCipher;
import com.security.cryptography.core.configuration.CryptographyKeyProperties;
import com.security.cryptography.core.utility.Converter;
import com.security.cryptography.domain.entity.SymmetricKeyBlockCipherData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

@Component
@AllArgsConstructor
public class AesImpl implements SymmetricKeyBlockCipher {
    private CryptographyKeyProperties cryptographyKeyProperties;

    @Override
    public SymmetricKeyBlockCipherData encrypt(final String ALGORITHM, char[] input)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        final Cipher CIPHER = Cipher.getInstance(ALGORITHM);

        final IvParameterSpec INITIALIZATION_VECTOR = generateIv();

        CIPHER.init(Cipher.ENCRYPT_MODE, generateKey(), INITIALIZATION_VECTOR);

        final byte[] CIPHER_TEXT = CIPHER.doFinal(Converter.toBytes(input));

        wipeArray(input);

        return SymmetricKeyBlockCipherData.builder()
                .initializationVector(Converter.toCharArray(INITIALIZATION_VECTOR.getIV()))
                .cipherText(Base64.getEncoder().encodeToString(CIPHER_TEXT))
                .build();
    }

    @Override
    public SecretKey generateKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        final SecretKeyFactory FACTORY = SecretKeyFactory.getInstance(cryptographyKeyProperties.getAlgorithmFunction());

        final KeySpec SPEC = new PBEKeySpec(cryptographyKeyProperties.getPassword(),
                                        Converter.toBytes(cryptographyKeyProperties.getSalt()),
                                        cryptographyKeyProperties.getIterationCount(),
                                        cryptographyKeyProperties.getKeyLength());

        return new SecretKeySpec(
                FACTORY.generateSecret(SPEC).getEncoded(), cryptographyKeyProperties.getAlgorithmKey());
    }

    @Override
    public IvParameterSpec generateIv() {
        byte[] iv = new byte[16];

        new SecureRandom().nextBytes(iv);

        return new IvParameterSpec(iv);
    }

    @Override
    public IvParameterSpec generateIv(char[] INITIALIZATION_VECTOR) {
        return new IvParameterSpec(Converter.toBytes(INITIALIZATION_VECTOR));
    }

    @Override
    public void wipeArray(char[] secret) {
        final int DUMMY_VALUE = 0;
        Arrays.fill(secret, (char) DUMMY_VALUE);
    }

    @Override
    public char[] decrypt(String ALGORITHM, SymmetricKeyBlockCipherData SYMMETRIC_KEY_BLOCK_CIPHER_DATA)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        final Cipher CIPHER = Cipher.getInstance(ALGORITHM);

        CIPHER.init(Cipher.DECRYPT_MODE, generateKey(), generateIv(SYMMETRIC_KEY_BLOCK_CIPHER_DATA.getInitializationVector()));

        final byte[] BYTE_DECRYPTED_TEXT = CIPHER.doFinal(Base64.getDecoder()
                                                .decode(SYMMETRIC_KEY_BLOCK_CIPHER_DATA.getCipherText()));

        return Converter.toCharArray(BYTE_DECRYPTED_TEXT);
    }
}
