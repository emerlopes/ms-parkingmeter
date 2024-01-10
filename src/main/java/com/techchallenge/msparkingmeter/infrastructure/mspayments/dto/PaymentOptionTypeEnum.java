package com.techchallenge.msparkingmeter.infrastructure.mspayments.dto;

import java.util.HashMap;
import java.util.Map;

public enum PaymentOptionTypeEnum {
    CREDIT_CARD(1L, "Cartão de Crédito"),
    DEBIT_CARD(2L, "Débito"),
    PIX(3L, "Pix");

    private Long value;
    private String displayName;

    private static final Map<Long, PaymentOptionTypeEnum> BY_VALUE = new HashMap<>();

    static {
        for (PaymentOptionTypeEnum paymentType : values()) {
            BY_VALUE.put(paymentType.value, paymentType);
        }
    }

    PaymentOptionTypeEnum(Long value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public Long getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PaymentOptionTypeEnum valueOf(Long value) {
        PaymentOptionTypeEnum paymentType = BY_VALUE.get(value);
        if (paymentType == null) {
            throw new IllegalArgumentException("No enum constant found with value: " + value);
        }
        return paymentType;
    }
}
