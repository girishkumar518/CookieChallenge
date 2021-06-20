package com.cookie.demo.service;
/*
 *  Created by Girish Kumar CH on 6/18/21
 *  This class will provide solution to the cookie business problems
 */

import com.cookie.demo.constants.ApplicationConstants;
import com.cookie.demo.constants.Errors;
import com.cookie.demo.dto.Cookie;
import com.cookie.demo.exception.ApplicationException;
import com.cookie.demo.utils.CookieUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;
import java.util.stream.Collectors;

public class CookieService {

    private static final Logger LOG = LogManager.getLogger(CookieService.class);

    /*
        Method is to get the Active Cookies By Date using java8 features Streams
        getActiveCookiesByDatePlainJava
     */
    public List<String> getActiveCookiesByDateUsingJava8Streams(Map<String,Cookie> cookieMap, String date){

        LOG.info("getActiveCookiesByDate cookieMap {} date received {}",cookieMap, date);

        //converting the string date to local date for comparision
        DateTime dateTime = null;

        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern(ApplicationConstants.INPUT_DATE_FORMAT);
            dateTime = DateTime.parse(date, formatter);
            dateTime.withZone(ApplicationConstants.TIME_ZONE);

        }catch(Exception  e){

            throw new ApplicationException(Errors.INVALID_DATE_FORMAT.getCode(),String.format(Errors.INVALID_DATE_FORMAT.getMessage(),date,ApplicationConstants.INPUT_DATE_FORMAT) );

        }
        LocalDate locaDate = CookieUtils.formatAndGetDateTime(date,ApplicationConstants.INPUT_DATE_FORMAT).toLocalDate();

        LOG.debug("converted dateTime Object {}",locaDate);
        Map<String, Long> countMap = cookieMap.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(),
                e -> e.getValue().getOccuredTimes().stream().filter(t -> t.toLocalDate().equals(locaDate)).count()));

        LOG.debug("Cookie count {}",countMap);

        Long maxValue = Collections.max(countMap.values());
        List<String> output = countMap.entrySet().stream().filter(e -> e.getValue()>0 && e.getValue().equals(maxValue)).map(Map.Entry::getKey).collect(Collectors.toList());

        LOG.info("final output {}",output);


        return output;
    }

    /*
       Method is to get the Active Cookies By Date without using java8 features Streams
    */
    public List<String> getActiveCookiesByDate(Map<String,Cookie> cookieMap, String date){

        LOG.info("getActiveCookiesByDate cookieMap {} date received {}",cookieMap, date);

        //converting the string date to local date for comparision
        DateTime dateTime = null;

        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern(ApplicationConstants.INPUT_DATE_FORMAT);
            dateTime = DateTime.parse(date, formatter);
            dateTime.withZone(ApplicationConstants.TIME_ZONE);

        }catch(Exception  e){

            throw new ApplicationException(Errors.INVALID_DATE_FORMAT.getCode(),String.format(Errors.INVALID_DATE_FORMAT.getMessage(),date,ApplicationConstants.INPUT_DATE_FORMAT) );

        }
        LocalDate locaDate = CookieUtils.formatAndGetDateTime(date,ApplicationConstants.INPUT_DATE_FORMAT).toLocalDate();

        LOG.debug("converted dateTime Object {}",locaDate);

        List<String> output = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        for (Cookie cookieEntry : cookieMap.values()) {

            int eachCount = 0;
            for (DateTime occuredTime : cookieEntry.getOccuredTimes()) {
                if(occuredTime.toLocalDate().equals(locaDate))
                    eachCount++;
            }

            if(eachCount > 0 && eachCount >= max) {
                if(eachCount > max) {
                    output = new ArrayList<>();
                    max = eachCount;
                }

                output.add(cookieEntry.getId());
            }

        }
        LOG.info("final output {}",output);


        return output;
    }

}
