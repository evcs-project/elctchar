package com.elct.elctchar.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalApiException extends RuntimeException{
    private String mesaage;
    public GlobalApiException(ErrorCode errorCode)
    {
        this.mesaage = errorCode.getName();
    }

    public GlobalApiException(String mesaage)
    {
        this.mesaage = mesaage;
    }
}
