package com.lightning.school.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.jwt")
public class SecurityDataConfig {

    /**
     * secret phrase of jwt
     */
    @Getter
    @Setter
    private String secret;
    /**
     * expiration of token
     */
    @Getter
    @Setter
    private Long expirationTime;
    /**
     * prefix token
     */
    @Getter
    @Setter
    private String tokenPrefix;
    /**
     * propertie header to paste a token
     */
    @Getter
    @Setter
    private String headerString;

}
