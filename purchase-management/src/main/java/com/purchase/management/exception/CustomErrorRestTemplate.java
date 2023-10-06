package com.purchase.management.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

public class CustomErrorRestTemplate implements ResponseErrorHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String jsonBody = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
        ErrorDetails errorDetails = objectMapper.readValue(jsonBody, ErrorDetails.class);
        if(response.getStatusCode() == HttpStatus.NOT_FOUND){
            throw new ResourceNotFoundException(errorDetails.getMessage());
        }
    }
}