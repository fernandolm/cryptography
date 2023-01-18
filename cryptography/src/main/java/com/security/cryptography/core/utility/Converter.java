package com.security.cryptography.core.utility;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@UtilityClass
public class Converter {
    public byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);

        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);

        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                                            byteBuffer.position(),
                                            byteBuffer.limit());

        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data

        return bytes;
    }

    public char[] toCharArray(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);

        String hex = bi.toString(array.length);

        int paddingLength = (array.length * 2) - hex.length();

        if(paddingLength > 0)
        {
            String result = String.format("%0" + paddingLength + "d", 0) + hex;

            return result.toCharArray();
        } else {
            return hex.toCharArray();
        }
    }
}
