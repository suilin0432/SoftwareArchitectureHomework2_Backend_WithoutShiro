package com.software.architecture.homework2.models.ExceptionConvetModel;


import com.software.architecture.homework2.models.exceptionModels.BaseException;

public class ExceptionEntity {
    public String message;
    public int code;

    public ExceptionEntity(BaseException exception) {
        this.message = exception.message;
        this.code = exception.code;
    }
}
