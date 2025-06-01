package com.map.Vale.Ponto.controllers.error;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}

