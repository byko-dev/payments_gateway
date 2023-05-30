package com.byko.payments_gateway_api.exceptions;

public class BadRequestException extends AppRuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
