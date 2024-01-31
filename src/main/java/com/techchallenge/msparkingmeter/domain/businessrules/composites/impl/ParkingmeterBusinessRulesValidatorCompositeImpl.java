package com.techchallenge.msparkingmeter.domain.businessrules.composites.impl;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.ExecuteCalculationFixedAmountExpectedToBePaidUseCaseImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementação da interface {@link ParkingmeterBusinessRulesValidatorComposite} responsável por validar regras de negócio compostas relacionadas a controles de estacionamento.
 */
@Component
public class ParkingmeterBusinessRulesValidatorCompositeImpl implements ParkingmeterBusinessRulesValidatorComposite {

    private final List<ParkingmeterBusinessRulesValidatorComposite> validators;
    private final ExecuteCalculationFixedAmountExpectedToBePaidUseCaseImpl executeCalculationFixedAmountExpectedToBePaidUseCaseImpl;
    private RuntimeException exception;

    /**
     * Construtor da classe.
     *
     * @param validators                                               Lista de validadores compostos relacionados a controles de estacionamento.
     * @param executeCalculationFixedAmountExpectedToBePaidUseCaseImpl Caso de uso para cálculo do valor fixo esperado a ser pago.
     */
    public ParkingmeterBusinessRulesValidatorCompositeImpl(List<ParkingmeterBusinessRulesValidatorComposite> validators,
                                                           ExecuteCalculationFixedAmountExpectedToBePaidUseCaseImpl executeCalculationFixedAmountExpectedToBePaidUseCaseImpl) {
        this.validators = validators;
        this.executeCalculationFixedAmountExpectedToBePaidUseCaseImpl = executeCalculationFixedAmountExpectedToBePaidUseCaseImpl;
    }

    /**
     * Adiciona um validador composto à lista de validadores.
     *
     * @param validator Validador composto a ser adicionado.
     */
    public void addValidator(ParkingmeterBusinessRulesValidatorComposite validator) {
        this.validators.add(validator);
    }

    /**
     * Valida se um controle de estacionamento é válido com base em regras de negócio compostas.
     *
     * @param input Entrada de domínio do controle de estacionamento a ser validado.
     * @return true se o controle de estacionamento é válido; false caso contrário.
     */
    @Override
    public boolean isValid(ParkingControlDomainEntityInput input) {
        final var periodTypeId = input.getPeriodType().getParkingControlPeriodId();
        final var isFixedPeriodType = this.isFixedPeriodType(PeriodTypeEnum.valueOf(periodTypeId));

        if (!isFixedPeriodType) {
            System.out.println("O tipo de período não é fixo");
            input.setDurationInMinutes(0);
            input.setPredictedValueToBePaid(BigDecimal.ZERO);
            return true;
        }

        try {
            for (ParkingmeterBusinessRulesValidatorComposite validator : validators) {
                if (!validator.isValid(input)) {
                    this.exception = validator.getException();
                    return false;
                }
            }
        } catch (Exception e) {
            this.exception = new RuntimeException("Erro ao validar regras de negocio", e);
            return false;
        }

        executeCalculationFixedAmountExpectedToBePaidUseCaseImpl.execute(input);

        return true;
    }

    /**
     * Obtém a exceção ocorrida durante a validação.
     *
     * @return A exceção ocorrida durante a validação.
     */
    @Override
    public RuntimeException getException() {
        return this.exception;
    }

    /**
     * Verifica se o tipo de período é fixo.
     *
     * @param periodType Tipo de período a ser verificado.
     * @return true se o tipo de período for fixo; false caso contrário.
     */
    private boolean isFixedPeriodType(PeriodTypeEnum periodType) {
        return periodType.equals(PeriodTypeEnum.FIXED);
    }
}

