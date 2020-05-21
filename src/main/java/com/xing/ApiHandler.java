package com.xing;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2ProxyRequestEvent;

public class ApiHandler implements RequestHandler<APIGatewayV2ProxyRequestEvent, String> {
    @Override
    public String handleRequest(APIGatewayV2ProxyRequestEvent input, Context context) {
        return "hello, apigateway";
    }
}
