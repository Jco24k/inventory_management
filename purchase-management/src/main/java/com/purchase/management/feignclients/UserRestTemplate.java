package com.purchase.management.feignclients;

import com.purchase.management.models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserRestTemplate {
    private String url = "http://localhost:9090";

    private final RestTemplate restTemplate;

    public UserModel findOne(Long userId){
        return restTemplate.getForObject(url+"/api/user/"+userId, UserModel.class);
    }
}
