package com.cookie.demo.utils;
/*
 *  Created by Girish Kumar CH on 6/18/21
 */

import com.cookie.demo.constants.ApplicationConstants;
import com.cookie.demo.constants.Errors;
import com.cookie.demo.exception.ApplicationException;
import com.cookie.demo.service.CookieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CookieUtils {

    private static final Logger LOG = LogManager.getLogger(CookieUtils.class);

    public static DateTime formatAndGetDateTime(String timestamp, String dateFormat) {

        DateTime dateTime = null;

        try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
                dateTime = DateTime.parse(timestamp, formatter);
                dateTime.withZone(ApplicationConstants.TIME_ZONE);

        }catch(Exception  e){

            LOG.error("Exception occured while parsing Date Exception={}",new ApplicationException(Errors.INVALID_DATE_FORMAT.getCode(),String.format(Errors.INVALID_DATE_FORMAT.getMessage(),timestamp,dateFormat) ));

        }

        return dateTime;

    }
}
