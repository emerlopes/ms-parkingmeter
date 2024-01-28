package com.techchallenge.msparkingmeter.domain.shared;

public interface IExecuteVoid<T> {
    void execute(T domainObject);
}
