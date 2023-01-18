package com.security.cryptography.service;

import com.security.cryptography.core.cipher.specification.Hash;
import com.security.cryptography.service.specification.HashService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArgonServiceImpl implements HashService {
    private final Hash ARGON;

    @Override
    public String encrypt(final char[] PLAIN_TEXT) {
        return ARGON.encrypt(PLAIN_TEXT);
    }

    @Override
    public boolean verify(final char[] PLAIN_TEXT, final String HASH) {
        return ARGON.verify(PLAIN_TEXT, HASH);
    }
}
