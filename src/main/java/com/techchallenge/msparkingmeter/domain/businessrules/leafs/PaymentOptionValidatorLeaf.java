package com.techchallenge.msparkingmeter.domain.businessrules.leafs;


import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.repositories.mspayments.IPaymentsClient;
import com.techchallenge.msparkingmeter.repositories.mspayments.dto.PaymentOptionTypeEnum;
import org.springframework.stereotype.Component;

/**
 * Implementação da interface {@link ParkingmeterBusinessRulesValidatorComposite} que valida a opção de pagamento para o controle de estacionamento.
 */
@Component
public class PaymentOptionValidatorLeaf implements ParkingmeterBusinessRulesValidatorComposite {

    private final IPaymentsClient paymentsClient;

    private ParkingControlValidationException parkingControlValidationException;

    /**
     * Construtor da classe.
     *
     * @param paymentsClient Cliente para acessar as informações de opção de pagamento.
     */
    public PaymentOptionValidatorLeaf(IPaymentsClient paymentsClient) {
        this.paymentsClient = paymentsClient;
    }

    /**
     * Valida a opção de pagamento para o controle de estacionamento.
     *
     * @param input Entrada de domínio do controle de estacionamento a ser validado.
     * @return true se a opção de pagamento for válida; false caso contrário.
     */
    @Override
    public boolean isValid(ParkingControlDomainEntityInput input) {


        try {
            final var paymentOption = paymentsClient.findPaymentOptionByExternalDriverId(input.getExternalDriverId());

            if (paymentOption.getData() == null) {
                parkingControlValidationException = new ParkingControlValidationException("Forma de pagamento não encontrada para o id do motorista externo.");
                return false;
            }

            final var paymentOptionType = paymentOption.getData().getPaymentOptionType().getPaymentOptionType();

            if (paymentOptionType.equals(PaymentOptionTypeEnum.CREDIT_CARD) || paymentOptionType.equals(PaymentOptionTypeEnum.DEBIT_CARD)) {
                parkingControlValidationException = new ParkingControlValidationException("O período de estacionamento fixo está disponível apenas para pagamentos via PIX, atualize seu método de pagamento.");
                return false;
            }

        } catch (Exception e) {
            parkingControlValidationException = new ParkingControlValidationException("Forma de pagamento não encontrada para o id do motorista externo.");
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
        return this.parkingControlValidationException;
    }
}

