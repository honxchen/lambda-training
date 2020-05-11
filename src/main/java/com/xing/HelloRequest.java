package com.xing;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class HelloRequest implements RequestHandler<Map<String,String>, String> {

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        return "hello lambda!";
    }
}
