package com.example.architecture.homework2_withoutshiro.models.exceptionModels;

public class EmailException extends Throwable {
    public String message;
    public int code;
    public EmailException(int code, String message){
        this.message = message;
        this.code = code;
    }
    public EmailException(){
    }
    public EmailException(Throwable e){
        this.message = e.getMessage();
    }
}
