package com.atguigu.test;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class MinioTest {
    @Test
    void testMinio() throws Exception {

        // 创建一个Minio的客户端对象
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://192.168.160.130:9001")
                .credentials("admin", "admin123456")
                .build();

        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("spzx-bucket").build());

        // 如果不存在，那么此时就创建一个新的桶
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("spzx-bucket").build());
        } else {  // 如果存在打印信息
            System.out.println("Bucket 'spzx-bucket' already exists.");
        }
        URL resource = this.getClass().getClassLoader().getResource("./img/1.jpg");
        FileInputStream fis = new FileInputStream(resource.getFile()) ;
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket("spzx-bucket")
                .stream(fis, fis.available(), -1)
                .object("1.jpg")
                .build();
        minioClient.putObject(putObjectArgs) ;

        // 构建fileUrl
        String fileUrl = "http://192.168.160.130:9001/spzx-bucket/1.jpg" ;
        System.out.println(fileUrl);
    }
    @Test
    public void test2(){
        Long id=1L;
        System.out.println(id==2);
    }
}
