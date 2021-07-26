package com.elct.elctchar.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.graalvm.compiler.serviceprovider.IsolateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        return new ErrorResponse(
                Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage()
                , HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler(GlobalApiException.class)
    public ErrorResponse handleGlobalApiException(GlobalApiException ex)
    {
        return new ErrorResponse(ex.getMesaage(), HttpStatus.BAD_REQUEST.value());
    }

    // TODO : enum error 처리
    @ExceptionHandler(BindException.class)
    protected ErrorResponse handleBindException(BindException ex) {
        log.error("handleBindException");
        return new ErrorResponse(
                ex.getBindingResult().getFieldError().isBindingFailure() ?
                        "필드 : [" + ex.getBindingResult().getFieldError().getField() + "] 타입이 맞지 않습니다.":
                        ex.getBindingResult().getFieldError().getDefaultMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse handleGlobalApiException(AccessDeniedException ex)
    {
        return new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handException(Exception ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
