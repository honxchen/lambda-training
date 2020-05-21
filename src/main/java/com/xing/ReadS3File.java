package com.xing;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class ReadS3File implements RequestHandler<ScheduledEvent, String> {
    @Override
    public String handleRequest(ScheduledEvent input, Context context) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion("ap-southeast-1").build();
        AmazonSQS sqsClient = AmazonSQSClient.builder().withRegion("ap-southeast-1").build();
        try {
            S3Object object = s3.getObject("hongxing-test-bucket", "README.md");
            String text = IOUtils.toString(object.getObjectContent(), "UTF-8");
            sqsClient.sendMessage("https://sqs.ap-southeast-1.amazonaws.com/494526681395/hongxing-stack-MessageQueue-1CVDGBRSWDUTR", text);
            return text;
        } catch (AmazonServiceException | IOException e) {
            return "error message " + e;
        }
    }
}
