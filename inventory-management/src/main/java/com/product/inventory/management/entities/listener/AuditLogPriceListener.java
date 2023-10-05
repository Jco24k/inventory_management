package com.product.inventory.management.entities.listener;

import com.product.inventory.management.entities.Product;
import com.product.inventory.management.entities.logs.LogPrice;
import com.product.inventory.management.repositories.LogPriceRepository;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Component
@Slf4j
public class AuditLogPriceListener {

    private final LogPriceRepository logPriceRepository;
    public AuditLogPriceListener(@Lazy LogPriceRepository logPriceRepository) {
        this.logPriceRepository = logPriceRepository;
    }

    private BigDecimal oldPrice = BigDecimal.ZERO;
    @PostLoad
    public void beforeUpdate(Product product){
      oldPrice = SerializationUtils.clone(product).getPrice().setScale(2,RoundingMode.HALF_UP);
    }

    @PostUpdate
    public void afterUpdate(Product product){
        BigDecimal newPrice = product.getPrice().setScale(2, RoundingMode.HALF_UP);
        if(!oldPrice.equals(newPrice)){
            logPriceRepository.save(
                    new LogPrice(oldPrice,newPrice,product)
            );
            log.info("LogPrice registered successfully!");
        }
    }
}
