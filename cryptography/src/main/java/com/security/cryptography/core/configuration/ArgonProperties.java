package com.security.cryptography.core.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.argon")
public class ArgonProperties {
    private int maximumMilliseconds = 60000; //1 minute
    private int maximumMemoryInKilobytes = 1048576; //1GB
    private int parallelismCoreNumber = 8;
}
