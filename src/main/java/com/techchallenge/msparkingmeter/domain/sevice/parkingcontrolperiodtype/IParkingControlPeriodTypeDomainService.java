package com.techchallenge.msparkingmeter.domain.sevice.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;

import java.util.List;

public interface IParkingControlPeriodTypeDomainService {

    ParkingControlPeriodTypeDomainEntityOutput saveParkingControlPeriodType(ParkingControlPeriodTypeDomainEntityInput input);

    List<ParkingControlPeriodTypeDomainEntityOutput> findAllParkingControlPeriodType();
}
