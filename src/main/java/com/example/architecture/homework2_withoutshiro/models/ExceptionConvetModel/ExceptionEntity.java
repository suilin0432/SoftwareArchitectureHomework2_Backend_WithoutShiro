package com.example.architecture.homework2_withoutshiro.models.ExceptionConvetModel;


import com.example.architecture.homework2_withoutshiro.models.exceptionModels.BaseException;

public class ExceptionEntity {
    public String message;
    public int code;

    public ExceptionEntity(BaseException exception) {
        this.message = exception.message;
        this.code = exception.code;
    }
}
