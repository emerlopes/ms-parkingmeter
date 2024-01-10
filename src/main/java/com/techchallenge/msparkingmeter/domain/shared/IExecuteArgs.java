package com.techchallenge.msparkingmeter.domain.shared;

public interface IExecuteArgs<T, J> {
    T execute(J domainObject);
}
