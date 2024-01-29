package com.techchallenge.msparkingmeter.domain.shared;

import lombok.experimental.Accessors;

@lombok.Data
@Accessors(chain = true)
public class CustomData<T> {

    private T data;

}
