package com.twitter.image.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {
    @Value("${amazon.aws.access-key}")
    private String awsAccessKey;

    @Value("${amazon.aws.secret-key}")
    private String awsAccessSecret;

   @Bean
   @Profile({"prod"})
   public AmazonS3 amazonS3() {
       AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsAccessSecret);
       return AmazonS3ClientBuilder.standard()
               .withCredentials(new AWSStaticCredentialsProvider(credentials))
               .withRegion(Regions.AP_SOUTHEAST_1)
               .build();
   }
}
