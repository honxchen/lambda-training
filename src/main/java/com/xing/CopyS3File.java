package com.xing;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyS3File implements RequestHandler<S3Event, String> {
    private static final Logger logger = LoggerFactory.getLogger(CopyS3File.class);

    @Override
    public String handleRequest(S3Event input, Context context) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion("ap-southeast-1").build();
        try {
            S3EventNotification.S3EventNotificationRecord record = input.getRecords().get(0);
            String srcBucket = record.getS3().getBucket().getName();
            String srcKey = record.getS3().getObject().getUrlDecodedKey();
            logger.info("receive log: " + srcBucket + " ," + srcKey);
            String[] paths = srcKey.split("/");
            String filename = paths[paths.length - 1];
            String dstBucket = srcBucket;
            String dstKey = "dest/" + filename;
            s3.copyObject(srcBucket, srcKey, dstBucket, dstKey);
            return "copy from src to dest";
        } catch (AmazonServiceException e) {
            return "error message " + e;
        }
    }
}
