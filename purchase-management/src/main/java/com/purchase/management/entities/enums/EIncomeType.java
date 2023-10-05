package com.purchase.management.entities.enums;

import lombok.Getter;

@Getter
public enum EIncomeType {
        PURCHASE,
        CUSTOMER_RETURN,
        DONATION_OR_GIFT,
        VENDOR_RETURN_OR_REPAIR,
        FREE_SAMPLES,
        OTHER
}
