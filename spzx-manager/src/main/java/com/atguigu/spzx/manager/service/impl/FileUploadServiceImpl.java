package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    MinioClient minioClient;
    @Override
    public String fileUpload(MultipartFile multipartFile, String module) {
        try {
            String s = UUID.randomUUID().toString();
            InputStream is = multipartFile.getInputStream();
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket("spzx-bucket")
                    .stream(is, is.available(), -1)
                    .object("module/"+s+".jpg")
                    .build();
            minioClient.putObject(putObjectArgs) ;

            // 构建fileUrl
            String fileUrl = "http://192.168.160.130:9001/spzx-bucket/"+"module/"+s+".jpg";
            return fileUrl;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        }

    }
}
