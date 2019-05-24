package com.lightning.school.mvc.delegate.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.SdkClientException;
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

    public String putMedia(MultipartFile multipartFile){

        String bucketName = mediaStoreConfig.getBucketName();
        String key = formalizeNameFile(multipartFile.getOriginalFilename());
        File file = null;
        try {
            file = copyInputStreamToFile(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        s3.putObject(new PutObjectRequest(bucketName, "public/"+key, file).withCannedAcl(CannedAccessControlList.PublicRead));

        return mediaStoreConfig.getBaseMedia() + key;
    }

    public boolean deleteMedia(String namefile){
        try {
            s3.deleteObject(mediaStoreConfig.getBucketName(), namefile);
        } catch (SdkClientException ex){
            return false;
        }
        return true;
    }

    private File copyInputStreamToFile(InputStream inputStream) {
        File file = null;
        try {
            file = File.createTempFile("temp-file", "");
            file.deleteOnExit();
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                int read;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private String formalizeNameFile(String nameFile){
        nameFile = nameFile.replaceAll("\\s+","");
        nameFile = nameFile.replaceAll("[\\@\\/\\:\\;\\!\\,\\?\\+\\-\\*\\(\\)\\{\\}\\#]","");
        return nameFile;
    }
}