package com.product.inventory.management.entities.listener;

import com.product.inventory.management.entities.Product;
import com.product.inventory.management.repositories.LogPriceRepository;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditLogPriceListener {

    private final ApplicationContext applicationContext;
    @PreUpdate
    public void beforeUpdate(Product product){
        LogPriceRepository logPriceRepository = applicationContext.getBean(LogPriceRepository.class);
      log.info("oldPrice: "+ product.getPrice());
    }

    @PostUpdate
    public void afterUpdate(Product product){
        log.info("newPrice: "+ product.getPrice());
    }
}
