package com.purchase.management.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.web.ErrorResponse;

import java.io.IOException;

public class CustomErrorFeignClient  implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int statusCode = response.status();
        if(statusCode == 404){
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = null;
            try {
                jsonBody = Util.toString(response.body().asReader(Util.UTF_8));
                ErrorDetails errorDetails = objectMapper.readValue(jsonBody, ErrorDetails.class);
                return new ResourceNotFoundException(errorDetails.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return new Exception();
    }
}