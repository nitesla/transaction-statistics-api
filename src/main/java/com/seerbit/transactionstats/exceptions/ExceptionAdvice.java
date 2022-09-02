package com.seerbit.transactionstats.exceptions;

import java.util.Map;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1000)
@RestControllerAdvice
public class ExceptionAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ HttpMessageNotReadableException.class })
  public void handleHttpMessageNotReadableException(
    HttpMessageNotReadableException ex
  ) {}

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler({ UnprocessableEntityException.class })
  public void handleUnprocessableException(UnprocessableEntityException ex) {}

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler({ MethodArgumentNotValidException.class })
  public void handleMethodArgumentNotValidException(
    MethodArgumentNotValidException ex
  ) {}
}
