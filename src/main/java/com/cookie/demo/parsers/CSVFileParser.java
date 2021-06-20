package com.cookie.demo.parsers;
/*
 *  Created by Girish Kumar CH on 6/17/21
 *  It is CSV File parser using CSV libraries to parse CSV files
 */

import com.cookie.demo.constants.ApplicationConstants;
import com.cookie.demo.constants.Errors;
import com.cookie.demo.dto.CSVHeaders;
import com.cookie.demo.dto.Cookie;
import com.cookie.demo.dto.CookieFactory;
import com.cookie.demo.exception.ApplicationException;
import com.cookie.demo.utils.CookieUtils;
import com.cookie.demo.utils.Validator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.*;
import java.util.Map;

public class CSVFileParser implements Parser{

    private static final Logger LOG = LogManager.getLogger(CSVFileParser.class);

    @Override
    public Map<String, Cookie> getParsedCookies(String filePath) throws IOException {

        LOG.debug("Getting cookie map for the csv file {} ",filePath);

        validate(filePath);

        File initialFile = new File(filePath);
        InputStream targetStream = new FileInputStream(initialFile);
        CSVParser csvParser =CSVFormat.DEFAULT.withHeader(CSVHeaders.class).parse(new InputStreamReader(targetStream));

        CookieFactory cookieFactory = CookieFactory.getInstance();

        for (CSVRecord record : csvParser.getRecords()) {

            String cookieId = record.get(CSVHeaders.cookie).trim();
            String timestamp = record.get(CSVHeaders.timestamp).trim();

           // LOG.debug("timestamp {} cookie {}",cookieId,timestamp);

            if(!timestamp.equalsIgnoreCase("timestamp")) {

                Cookie cookie = cookieFactory.getCookie(cookieId);

                DateTime dateTime = null;

                try {
                    DateTimeFormatter formatter = DateTimeFormat.forPattern(ApplicationConstants.FILE_DATE_FORMAT);
                    dateTime = DateTime.parse(timestamp, formatter);
                    dateTime.withZone(ApplicationConstants.TIME_ZONE);

                }catch(Exception  e){

                    LOG.error("Exception occured while parsing Date Exception={}",new ApplicationException(Errors.INVALID_DATE_FORMAT.getCode(),String.format(Errors.INVALID_DATE_FORMAT.getMessage(),timestamp,ApplicationConstants.FILE_DATE_FORMAT) ));

                }

                cookie.addOccuredTime(dateTime);
            }

        }

        return cookieFactory.getCookieMap();
    }

    @Override
    public boolean validate(String filePath) {

        if(Parser.super.validate(filePath))
          return Validator.validateCSVFile(filePath);

        return false;
    }
}
