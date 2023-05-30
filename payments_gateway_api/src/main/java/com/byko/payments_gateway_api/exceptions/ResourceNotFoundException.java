package com.byko.payments_gateway_api.exceptions;

public class ResourceNotFoundException extends AppRuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
