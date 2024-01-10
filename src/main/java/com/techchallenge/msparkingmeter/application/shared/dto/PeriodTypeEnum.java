package com.techchallenge.msparkingmeter.application.shared.dto;

import java.util.HashMap;
import java.util.Map;

public enum PeriodTypeEnum {
    FIXED(1L, "Período Fixo"),
    VARIABLE(2L, "Período Variável");

    private Long value;
    private String displayName;

    private static final Map<Long, PeriodTypeEnum> BY_VALUE = new HashMap<>();

    static {
        for (PeriodTypeEnum periodType : values()) {
            BY_VALUE.put(periodType.value, periodType);
        }
    }

    PeriodTypeEnum(Long value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public Long getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PeriodTypeEnum valueOf(Long value) {
        PeriodTypeEnum periodType = BY_VALUE.get(value);
        if (periodType == null) {
            throw new IllegalArgumentException("No enum constant found with value: " + value);
        }
        return periodType;
    }
}
