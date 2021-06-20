package com.cookie.demo.parsers;

import com.cookie.demo.dto.Cookie;
import com.cookie.demo.utils.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface Parser {

    public Map<String,Cookie> getParsedCookies(String filePath) throws IOException;

    default boolean validate(String filePath){

        return Validator.validateFileExisted(filePath);

    }

}
