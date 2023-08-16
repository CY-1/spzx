package com.atguigu.spzx.manager;

import com.atguigu.spzx.common.config.EnableKnife4j;
import com.atguigu.spzx.manager.properties.UserAuthProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@SpringBootApplication
@EnableConfigurationProperties
@EnableKnife4j
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class , args) ;
    }
    @Bean
    public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://192.168.160.130:9001")
                .credentials("admin", "admin123456")
                .build();
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("spzx-bucket").build());

        // 如果不存在，那么此时就创建一个新的桶
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("spzx-bucket").build());
        }
        return minioClient;
    }
}