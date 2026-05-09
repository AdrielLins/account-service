package org.treasure.accountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends AbstractException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.name(), message);
    }
}
