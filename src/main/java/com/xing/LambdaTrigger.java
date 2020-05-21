package com.xing;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;

import java.nio.charset.Charset;
import java.util.Map;

public class LambdaTrigger implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String input, Context context) {
        String funcName = input;
        LambdaClient client = LambdaClient.builder().region(Region.AP_SOUTHEAST_1).build();
        client.invoke(InvokeRequest.builder()
                .functionName(funcName)
                .invocationType("RequestResponse")
                .payload(SdkBytes.fromString("{\"message\": \"message from lambda\"}", Charset.forName("UTF-8")))
                .build());
        return "trigged function " + funcName;
    }
}
