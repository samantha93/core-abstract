package com.example.demo.core.impl.service.com.example.demo.core.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoEnoughSpaceException extends RuntimeException {

  public NoEnoughSpaceException(String message) {
    super(message);
  }
}
