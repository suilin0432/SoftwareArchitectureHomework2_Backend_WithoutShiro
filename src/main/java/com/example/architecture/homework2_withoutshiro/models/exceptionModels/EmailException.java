package com.software.architecture.homework2.models.exceptionModels;

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
