package com.cookie.demo.utils;/*
 *  Created by Girish Kumar CH on 6/17/21
 */

import com.cookie.demo.constants.ApplicationConstants;
import com.cookie.demo.constants.Errors;
import com.cookie.demo.exception.ApplicationException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validateCSVFile(final String csvFile){

        final Pattern pattern = Pattern.compile(ApplicationConstants.CSV_REGEX);
        final Matcher matcher = pattern.matcher(csvFile);

        if(!matcher.matches())
            throw new ApplicationException(Errors.INVALID_CSV_FILE.getCode(),Errors.INVALID_CSV_FILE.getMessage());


        return true;

    }

    public static boolean validateFileExisted(String filePath){
        final Path path = Paths.get(filePath);

        if(!Files.exists(path))
            throw new ApplicationException(Errors.FILE_NOT_EXISTED.getCode(),String.format(Errors.FILE_NOT_EXISTED.getMessage(),filePath));

        return true;
    }


}
