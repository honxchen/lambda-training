package com.xing;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

public class HelloRequest implements RequestHandler<Map<String,String>, String> {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public String handleRequest(Map<String,String> input, Context context) {
        if (0 == input.size()) {
            input.put("message", "default");
        }
        AmazonSQS sqsClient = AmazonSQSClient.builder().withRegion("ap-southeast-1").build();
        sqsClient.sendMessage("https://sqs.ap-southeast-1.amazonaws.com/494526681395/hongxing-stack-MessageQueue-1CVDGBRSWDUTR", gson.toJson(input));
        return "receive message : " + gson.toJson(input) + ", and send to sqs";
    }
}
