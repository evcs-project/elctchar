package com.elct.elctchar.web.exception;

import lombok.Getter;

@Getter
public class GlobalApiException extends RuntimeException{
    private String mesaage;

    public GlobalApiException(ErrorCode errorCode)
    {
        this.mesaage = errorCode.getName();
    }
}
