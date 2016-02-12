package com.company.admin.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by aandra1 on 12/02/16.
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {

  public UnprocessableEntityException(String message, Throwable cause) {
    super(message, cause);
  }
}
