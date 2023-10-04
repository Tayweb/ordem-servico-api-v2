package com.web.ordemservico.service.exception;

public class DataIntegratyViolationException extends RuntimeException{
    public DataIntegratyViolationException(String message){
        super(message);
    }
}
