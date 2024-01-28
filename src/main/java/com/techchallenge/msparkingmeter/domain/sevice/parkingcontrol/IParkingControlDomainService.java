package com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;

public interface IParkingControlDomainService {
    ParkingControlDomainEntityOutput saveParkingControl(ParkingControlDomainEntityInput input);

    ParkingControlDomainEntityOutput findParkingControlById(Long id);
}
