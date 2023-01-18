package com.security.cryptography.core.cipher;

import com.security.cryptography.core.cipher.specification.Hash;
import com.security.cryptography.core.configuration.ArgonProperties;
import de.mkammerer.argon2.Argon2Helper;
import org.springframework.stereotype.Component;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Component
public class ArgonImpl implements Hash {
    private final Argon2 ARGON2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
    private int MAXIMUM_MILLISECONDS;
    private int MAXIMUM_MEMORY_IN_KILOBYTES;
    private int PARALLELISM_CORE_NUMBER;
    private int NUMBER_OF_ITERATIONS;

    private ArgonImpl() {}

    public ArgonImpl(ArgonProperties argonProperties) {
        MAXIMUM_MILLISECONDS = argonProperties.getMaximumMilliseconds();
        MAXIMUM_MEMORY_IN_KILOBYTES = argonProperties.getMaximumMemoryInKilobytes();
        PARALLELISM_CORE_NUMBER = argonProperties.getParallelismCoreNumber();

        NUMBER_OF_ITERATIONS = Argon2Helper.findIterations(ARGON2,
                                                            MAXIMUM_MILLISECONDS,
                                                            MAXIMUM_MEMORY_IN_KILOBYTES,
                                                            PARALLELISM_CORE_NUMBER);
    }

    @Override
    public String encrypt(final char[] PLAIN_TEXT) {
        try {
            return ARGON2.hash(NUMBER_OF_ITERATIONS,
                            MAXIMUM_MEMORY_IN_KILOBYTES,
                            PARALLELISM_CORE_NUMBER,
                            PLAIN_TEXT);
        } finally {
            ARGON2.wipeArray(PLAIN_TEXT);
        }
    }

    @Override
    public boolean verify(final char[] PLAIN_TEXT, final String HASH) {
        try {
            return ARGON2.verify(HASH, PLAIN_TEXT);
        } finally {
            ARGON2.wipeArray(PLAIN_TEXT);
        }
    }
}
