package com.company.blogsearch.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        try {
            String requestLog = makeRequestLog(request, body);
            log.info("request : {}", requestLog);

            ClientHttpResponse response = execution.execute(request, body);

            String responseLog = makeResponseLog(response, request.getURI());

            log.info("response : {}", responseLog);

            return response;
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            throw e;
        }
    }

    private String makeResponseLog(ClientHttpResponse response, URI uri) throws IOException {
        return "uri : " + uri +
                ", status code : " + response.getStatusCode() +
                ", resposne body" + new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8)
                .replaceAll("\n", "");
    }

    private String makeRequestLog(HttpRequest request, byte[] body) {
        return "uri : " + request.getURI() +
                ", method : " + request.getMethod() +
                ", request body : " + new String(body, StandardCharsets.UTF_8);
    }
}
