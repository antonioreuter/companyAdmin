package com.company.admin.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by aandra1 on 10/02/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Sorry! We couldn't find the resource that you were looking for. ")
public class ResourceNotFoundException extends RuntimeException {
}
