package com.techchallenge.msparkingmeter.application.exceptions;

public class ParkingControlNotFoundException extends RuntimeException {
    public ParkingControlNotFoundException(String message) {
        super(message);
    }
}
