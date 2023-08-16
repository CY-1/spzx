package com.atguigu.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="spzx.minio") //读取节点
public class MinioProperties {

    private String endpointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;

}