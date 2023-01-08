package com.emsi.pfe.exception;

public class ReservationAlreadyDoneException extends Exception{

    public ReservationAlreadyDoneException(String errorMessage) {
        super(errorMessage);
    }
}
