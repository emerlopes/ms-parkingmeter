package com.techchallenge.msparkingmeter.domain.businessrules.leafs;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import org.springframework.stereotype.Component;

/**
 * Implementação da interface {@link ParkingmeterBusinessRulesValidatorComposite} que valida se a duração mínima em minutos é atendida para o período de estacionamento fixo.
 */
@Component
public class MinimumMinutesDurationValidatorLeaf implements ParkingmeterBusinessRulesValidatorComposite {

    private ParkingControlValidationException parkingControlValidationException;

    /**
     * Duração mínima, em minutos, para o período de estacionamento fixo.
     */
    private final static int MINIMUM_DURATION_IN_MINUTES = 60;

    /**
     * Valida se a duração mínima em minutos é atendida para o período de estacionamento fixo.
     *
     * @param input Entrada de domínio do controle de estacionamento a ser validado.
     * @return true se a duração mínima for atendida; false caso contrário.
     */
    @Override
    public boolean isValid(ParkingControlDomainEntityInput input) {

        final var durationInMinutes = input.getDurationInMinutes();

        if (durationInMinutes < MINIMUM_DURATION_IN_MINUTES) {
            parkingControlValidationException = new ParkingControlValidationException("A duração mínima para um período de estacionamento fixo é de 60 minutos");
            return false;
        }

        return true;
    }

    /**
     * Obtém a exceção ocorrida durante a validação.
     *
     * @return A exceção ocorrida durante a validação.
     */
    @Override
    public RuntimeException getException() {
        return parkingControlValidationException;
    }
}

