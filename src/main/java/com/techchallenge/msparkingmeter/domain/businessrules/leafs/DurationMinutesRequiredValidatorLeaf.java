package com.techchallenge.msparkingmeter.domain.businessrules.leafs;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import org.springframework.stereotype.Component;

/**
 * Implementação da interface {@link ParkingmeterBusinessRulesValidatorComposite} que valida se a duração em minutos é obrigatória quando o tipo de período é fixo.
 */
@Component
public class DurationMinutesRequiredValidatorLeaf implements ParkingmeterBusinessRulesValidatorComposite {

    private ParkingControlValidationException parkingControlValidationException;

    /**
     * Valida se a duração em minutos é obrigatória quando o tipo de período é fixo.
     *
     * @param input Entrada de domínio do controle de estacionamento a ser validado.
     * @return true se a duração em minutos for válida; false caso contrário.
     */
    @Override
    public boolean isValid(ParkingControlDomainEntityInput input) {

        final var durationInMinutes = input.getDurationInMinutes();

        if (durationInMinutes == null || durationInMinutes == 0) {
            parkingControlValidationException = new ParkingControlValidationException("A duração em minutos é obrigatória quando o tipo de período é fixo");
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

