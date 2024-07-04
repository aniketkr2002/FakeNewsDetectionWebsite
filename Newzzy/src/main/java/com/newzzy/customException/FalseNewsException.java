package com.newzzy.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FalseNewsException extends RuntimeException {

    public FalseNewsException(String message) {
        super(message);
    }
    
}
