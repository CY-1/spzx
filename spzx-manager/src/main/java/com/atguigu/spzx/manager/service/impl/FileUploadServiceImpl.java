package com.atguigu.spzx.manager.service.impl;

import com.atguigu.model.entity.system.SysUser.AuthContextUtil;
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

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    MinioClient minioClient;
    @Override
    public String fileUpload(MultipartFile multipartFile) {
        try {
            InputStream is = multipartFile.getInputStream();
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket("spzx-bucket")
                    .stream(is, is.available(), -1)
                    .object(AuthContextUtil.get().getId().toString()+".jpg")
                    .build();
            minioClient.putObject(putObjectArgs) ;

            // 构建fileUrl
            String fileUrl = "http://192.168.160.130:9001/spzx-bucket/"+AuthContextUtil.get().getId().toString()+".jpg" ;
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
