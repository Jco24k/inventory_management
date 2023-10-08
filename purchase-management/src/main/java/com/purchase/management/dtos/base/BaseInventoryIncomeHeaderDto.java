package com.purchase.management.dtos.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseInventoryIncomeHeaderDto {

    private Long userId;
    private Long providerId;
    private Long purchaseOrderId;
    private Long warehouseId;

}
