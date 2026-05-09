package org.treasure.accountservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractException  extends  RuntimeException {

    private String code;
    private String message;

    public AbstractException() {
    }

    public AbstractException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
