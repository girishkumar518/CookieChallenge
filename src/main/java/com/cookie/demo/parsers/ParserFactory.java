package com.cookie.demo.parsers;/*
 *  Created by Girish Kumar CH on 6/18/21
 */

import com.cookie.demo.constants.ApplicationConstants;

public class ParserFactory {

    private static final ParserFactory parserFactory = new ParserFactory();

    private ParserFactory(){}

    public static ParserFactory getInstance(){ return parserFactory;}

    public Parser getParser(String fileType){

        if(fileType.equalsIgnoreCase(ApplicationConstants.FORMAT_CSV))
           return new CSVFileParser();

        return null;

    }

}
