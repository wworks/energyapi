package com.energy.admin.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.nio.file.Paths;

@Service
public class S3Service {

    private final S3Client s3Client;

    public S3Service() {
        String aws_access_key_id = "AKIA<placeholder>";
        String aws_secret_access_key = "http://canarytokens.com/traffic/feedback/images/t8ry4h85lzesjpl8asf3kztu0/contact.php";
 
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);

        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_2) 
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }

    public String downloadFile(String bucketName, String key, String downloadPath) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.getObject(getObjectRequest, Paths.get(downloadPath));
            return "File downloaded successfully to: " + downloadPath;
        } catch (S3Exception e) {
            return "Failed to download file: " + e.getMessage();
        }
    }
}
