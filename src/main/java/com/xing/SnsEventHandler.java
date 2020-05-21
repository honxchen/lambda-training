package com.xing;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;

import java.util.stream.Collectors;


public class SnsEventHandler implements RequestHandler<SNSEvent, String> {

    @Override
    public String handleRequest(SNSEvent input, Context context) {
        AmazonSQS sqsClient = AmazonSQSClient.builder().withRegion("ap-southeast-1").build();
        input.getRecords().stream()
                .map(record -> record.getSNS())
                .map(sns -> sns.getMessage())
                .forEach(message -> sqsClient.sendMessage("https://sqs.ap-southeast-1.amazonaws.com/494526681395/hongxing-stack-MessageQueue-1CVDGBRSWDUTR", message));
        return "receive message and send to sqs";
    }
}
