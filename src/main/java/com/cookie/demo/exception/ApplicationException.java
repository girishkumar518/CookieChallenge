package com.cookie.demo.exception;
/*
 *  Created by Girish Kumar CH on 6/17/21
 */

public class ApplicationException extends RuntimeException{

    private String errorCode;
    private String errorMessage;

    public ApplicationException(String errorCode , String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
