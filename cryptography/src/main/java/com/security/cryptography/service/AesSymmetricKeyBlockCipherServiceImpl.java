package com.security.cryptography.service;

import com.security.cryptography.core.cipher.specification.SymmetricKeyBlockCipher;
import com.security.cryptography.core.mapper.specification.SymmetricKeyBlockCipherDataMapper;
import com.security.cryptography.domain.dto.SymmetricKeyBlockCipherDataDto;
import com.security.cryptography.service.specification.SymmetricKeyBlockCipherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AesSymmetricKeyBlockCipherServiceImpl implements SymmetricKeyBlockCipherService {
    @Value("app.aes.algorithm")
    private String ALGORITHM_MODE_PADDING = "AES/CBC/PKCS5Padding";

    private final SymmetricKeyBlockCipher SYMMETRIC_KEY_BLOCK_CIPHER;
    private final SymmetricKeyBlockCipherDataMapper SYMMETRIC_KEY_BLOCK_CIPHER_MAPPER;

    @Override
    public SymmetricKeyBlockCipherDataDto encrypt(char[] input)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        return SYMMETRIC_KEY_BLOCK_CIPHER_MAPPER.map(
                SYMMETRIC_KEY_BLOCK_CIPHER.encrypt(ALGORITHM_MODE_PADDING, input));
    }

    @Override
    public char[] decrypt(final SymmetricKeyBlockCipherDataDto SYMMETRIC_KEY_BLOCK_CIPHER_DATA_DTO)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        return SYMMETRIC_KEY_BLOCK_CIPHER.decrypt(ALGORITHM_MODE_PADDING,
                SYMMETRIC_KEY_BLOCK_CIPHER_MAPPER.map(SYMMETRIC_KEY_BLOCK_CIPHER_DATA_DTO));
    }
}
