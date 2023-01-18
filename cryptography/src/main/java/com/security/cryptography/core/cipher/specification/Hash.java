package com.security.cryptography.core.cipher.specification;

public interface Hash {
    String encrypt(final char[] PLAIN_TEXT);
    boolean verify(final char[] PLAIN_TEXT, final String HASH);
}