package com.elct.elctchar.web.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ErrorResponse {

    private String message;
    private int statusCode;

    public ErrorResponse(String message, int statusCode)
    {
        this.message = message;
        this.statusCode = statusCode;
    }
}
