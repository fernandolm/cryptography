package com.security.cryptography.api.controller;

import com.security.cryptography.api.controller.specification.SymmetricKeyBlockCipherApi;
import com.security.cryptography.domain.dto.SymmetricKeyBlockCipherDataDto;
import com.security.cryptography.service.specification.SymmetricKeyBlockCipherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(value = "/api/symmetric-key-block-cipher", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SymmetricKeyBlockCipherController implements SymmetricKeyBlockCipherApi {
    private final SymmetricKeyBlockCipherService AES_SYMMETRIC_KEY_BLOCK_CIPHER_SERVICE;

    @Override
    @PostMapping(value = "/encrypt")
    public ResponseEntity<SymmetricKeyBlockCipherDataDto> encrypt(@RequestBody char[] plainText)
                                                        throws InvalidAlgorithmParameterException, NoSuchPaddingException,
                                                        IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
                                                        InvalidKeySpecException, InvalidKeyException {
        return ResponseEntity.ok(AES_SYMMETRIC_KEY_BLOCK_CIPHER_SERVICE.encrypt(plainText));
    }

    @Override
    @PostMapping(value = "/decrypt")
    public ResponseEntity<char[]> decrypt(@RequestBody SymmetricKeyBlockCipherDataDto SYMMETRIC_KEY_BLOCK_CIPHER_DATA_DTO)
                                throws InvalidAlgorithmParameterException, NoSuchPaddingException,
                                IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
                                InvalidKeySpecException, InvalidKeyException {
        return ResponseEntity.ok(AES_SYMMETRIC_KEY_BLOCK_CIPHER_SERVICE.decrypt(SYMMETRIC_KEY_BLOCK_CIPHER_DATA_DTO));
    }
}
