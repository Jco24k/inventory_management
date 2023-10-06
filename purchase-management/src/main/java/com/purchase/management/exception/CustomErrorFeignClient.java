package com.purchase.management.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class CustomErrorFeignClient  implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int statusCode = response.status();
        if(statusCode == HttpStatus.NOT_FOUND.value()){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonBody = Util.toString(response.body().asReader(Util.UTF_8));
                ErrorDetails errorDetails = objectMapper.readValue(jsonBody, ErrorDetails.class);
                return new ResourceNotFoundException(errorDetails.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return new Exception();
    }
}