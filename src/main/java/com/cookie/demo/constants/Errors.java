package com.cookie.demo.constants;/*
 *  Created by Girish Kumar CH on 6/17/21
 */

public enum Errors {

    INVALID_CSV_FILE        ("ERROR_0001","Please provide CSV file in the path"),
    FILE_NOT_EXISTED        ("ERROR_0002","File not existed on the path %s , please provide valid path"),
    INVALID_DATE_FORMAT     ("ERROR_0003","Invalid Date format %s expected date format %s");


    private final String code;
    private final String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {return code;}
    public String getMessage() {return message;}

    @Override
    public String toString() {
        return "Errors{" +
                "errorCode='" + code + '\'' +
                ", errorMessage='" + message + '\'' +
                '}';
    }

}
