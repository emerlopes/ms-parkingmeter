package com.techchallenge.msparkingmeter.application.shared.dto;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum PeriodTypeEnum {
    FIXED(1L, "Período Fixo", "Prezado motorista! Informamos que o período de estacionamento está prestes a se encerrar. Para evitar custos adicionais, solicitamos que renove ou encerre o estacionamento adequadamente. Lembre-se de que a não realização de um desses processos pode resultar em possíveis multas. Agradecemos pela sua atenção e colaboração."),
    VARIABLE(2L, "Período Variável", "Atenção: Seu estacionamento será estendido automaticamente por mais uma hora, a menos que você o desligue manualmente.");


    private Long value;
    private String displayName;
    private String message;

    private static final Map<Long, PeriodTypeEnum> BY_VALUE = new HashMap<>();

    static {
        for (PeriodTypeEnum periodType : values()) {
            BY_VALUE.put(periodType.value, periodType);
        }
    }

    PeriodTypeEnum(Long value, String displayName, String message) {
        this.value = value;
        this.displayName = displayName;
        this.message = message;
    }

    public static PeriodTypeEnum valueOf(Long value) {
        PeriodTypeEnum periodType = BY_VALUE.get(value);
        if (periodType == null) {
            throw new ParkingControlValidationException("No enum constant found with value: " + value);
        }
        return periodType;
    }

    public static boolean isFixed(Long value) {
        return value == FIXED.getValue();
    }
}
