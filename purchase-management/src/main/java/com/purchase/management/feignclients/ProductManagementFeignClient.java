package com.purchase.management.feignclients;

import com.purchase.management.models.ProductModel;
import com.purchase.management.models.ProviderModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-management", url= "http://localhost:9091/api")
public interface ProductManagementFeignClient {
    @GetMapping("/product/{id}")
    ProductModel findOneProduct(@PathVariable Long id);

    @GetMapping("/provider/{id}")
    ProviderModel findOneProvider(@PathVariable Long id);
}
