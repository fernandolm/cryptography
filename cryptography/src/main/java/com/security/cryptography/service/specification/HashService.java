package com.security.cryptography.service.specification;

public interface HashService {
    String encrypt(final char[] PLAIN_TEXT);
    boolean verify(final char[] PLAIN_TEXT, final String HASH);
}
