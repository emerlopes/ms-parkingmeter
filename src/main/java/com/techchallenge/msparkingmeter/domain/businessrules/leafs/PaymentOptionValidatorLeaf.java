package com.techchallenge.msparkingmeter.domain.businessrules.leafs;


import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.repositories.mspayments.IPaymentsClient;
import com.techchallenge.msparkingmeter.repositories.mspayments.dto.PaymentOptionTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class PaymentOptionValidatorLeaf implements ParkingmeterBusinessRulesValidatorComposite {

    private final IPaymentsClient paymentsClient;

    private ParkingControlValidationException parkingControlValidationException;

    public PaymentOptionValidatorLeaf(IPaymentsClient paymentsClient) {
        this.paymentsClient = paymentsClient;
    }

    @Override
    public boolean isValid(ParkingControlDomainEntityInput input) {

        final var paymentOption = paymentsClient.findPaymentOptionByExternalDriverId(input.getExternalDriverId());

        if (paymentOption.getData() == null) {
            parkingControlValidationException = new ParkingControlValidationException("Payment option not found");
            return false;
        }

        final var paymentOptionType = paymentOption.getData().getPaymentOptionType().getPaymentOptionType();

        if (paymentOptionType.equals(PaymentOptionTypeEnum.CREDIT_CARD) || paymentOptionType.equals(PaymentOptionTypeEnum.DEBIT_CARD)) {
            parkingControlValidationException = new ParkingControlValidationException("The fixed parking period is only available for payments via PIX, please update your payment method.");

            return false;
        }

        return true;
    }

    @Override
    public RuntimeException getException() {
        return this.parkingControlValidationException;
    }
}
