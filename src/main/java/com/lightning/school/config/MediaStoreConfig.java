package com.lightning.school.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws.s3")
public class MediaStoreConfig {

    @Getter
    @Setter
    private String bucketName;

    @Getter
    @Setter
    private String region;

    @Getter
    @Setter
    private String baseMedia;

}
