package com.openclassrooms.mddapi.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiCustomError extends RuntimeException {
    private String msg;
    private HttpStatus httpStatus;

    public ApiCustomError(String msg, HttpStatus httpStatus){
        super(msg);
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
