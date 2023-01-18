package com.security.cryptography.core.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.cryptography.key")
public class CryptographyKeyProperties {
    private String algorithmFunction = "PBKDF2WithHmacSHA256";
    private int iterationCount = 65536;
    private int keyLength = 256;
    private String algorithmKey = "AES";
    private char[] password = "TPwa0pn7bj5VQuNnQ2tWN63brFZ6Ab9ivD5duZFcfkhF3YluBYoDFfgokm4k$klogFoddsSODio3r3ijm302ko18a0148sdf".toCharArray();
    private char[] salt = "e(D]Bc3bL4+p).#Gf]!a3@WsUC4WGY}=&sh'[qGa+wZ&zIX#7u3m>w=WH4'i.\"k+_6?/#!FJzb.R?a$3)y'eoGTr*TQ)+p_@KX,q".toCharArray();
}
