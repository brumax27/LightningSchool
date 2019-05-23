package com.lightning.school.mvc.delegate.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lightning.school.config.MediaStoreConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class MediaStoreService {

    private MediaStoreConfig mediaStoreConfig;
    private AmazonS3 s3;

    @Autowired
    public MediaStoreService(MediaStoreConfig mediaStoreConfig) {
        this.mediaStoreConfig = mediaStoreConfig;
    }

    @PostConstruct
    public void init(){
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }

        s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(mediaStoreConfig.getRegion())
                .build();
    }

    public String putMedia(String nameFile, MultipartFile multipartFile){

        String bucketName = mediaStoreConfig.getBucketName();
        String key = nameFile.trim();
        key = key.replaceAll("[0-9\\(\\)]*","-");

        File file = null;
        try {
            file = File.createTempFile("aws-java-sdk-", "");
            file.deleteOnExit();
            copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PutObjectRequest objectRequest = new PutObjectRequest(bucketName, "public/"+key, file).withCannedAcl(CannedAccessControlList.PublicRead);
        s3.putObject(objectRequest);

        return mediaStoreConfig.getBaseMedia() + key;
    }

    private void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }
}
