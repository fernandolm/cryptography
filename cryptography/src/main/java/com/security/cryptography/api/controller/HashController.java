package com.security.cryptography.api.controller;

import com.security.cryptography.api.controller.specification.HashApi;
import com.security.cryptography.service.specification.HashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/hash", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HashController implements HashApi {
    private final HashService ARGON_SERVICE;

    @Override
    @PostMapping(value = "/encrypt")
    public ResponseEntity<String> encrypt(final char[] PLAIN_TEXT) {
        return ResponseEntity.ok(ARGON_SERVICE.encrypt(PLAIN_TEXT));
    }

    @Override
    @PostMapping(value = "/verify")
    public ResponseEntity<Boolean> verify(final char[] PLAIN_TEXT, final String HASH) {
        return ResponseEntity.ok(ARGON_SERVICE.verify(PLAIN_TEXT, HASH));
    }
}
