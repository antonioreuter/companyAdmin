package com.company.admin.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by aandra1 on 10/02/16.
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PreconditionFailedException extends RuntimeException {
}
