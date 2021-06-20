package com.cookie.demo.service;
/*
 *  Created by Girish Kumar CH on 6/18/21
 */

import com.cookie.demo.CookieApplicationTest;
import com.cookie.demo.constants.ApplicationConstants;
import com.cookie.demo.constants.Errors;
import com.cookie.demo.dto.Cookie;
import com.cookie.demo.exception.ApplicationException;
import com.cookie.demo.utils.CookieUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CookieServiceTest {

    private static String getFilePath(String fileName){

        ClassLoader classLoader = CookieApplicationTest.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        return file.getAbsolutePath();

    }

    public static Stream<Arguments> cookieMap(){


        Cookie c1 = new Cookie("SAZuXPGUrfbcn5UA");
        c1.addOccuredTime(CookieUtils.formatAndGetDateTime("2018-12-09T10:13:00+00:00", ApplicationConstants.FILE_DATE_FORMAT));
        c1.addOccuredTime(CookieUtils.formatAndGetDateTime("2018-12-09T08:18:00+00:00", ApplicationConstants.FILE_DATE_FORMAT));

        Cookie c2 = new Cookie("AtY0laUfhglK3lC7");
        c2.addOccuredTime(CookieUtils.formatAndGetDateTime("2018-12-10T10:13:00+00:00", ApplicationConstants.FILE_DATE_FORMAT));
        c2.addOccuredTime(CookieUtils.formatAndGetDateTime("2018-12-10T08:18:00+00:00", ApplicationConstants.FILE_DATE_FORMAT));

        Cookie c3 = new Cookie("5UAVanZf6UtGyKVS");
        c3.addOccuredTime(CookieUtils.formatAndGetDateTime("2018-12-10T10:13:00+00:00", ApplicationConstants.FILE_DATE_FORMAT));
        c3.addOccuredTime(CookieUtils.formatAndGetDateTime("2018-12-10T08:18:00+00:00", ApplicationConstants.FILE_DATE_FORMAT));


        Map<String, Cookie> cookieMap = new HashMap<>();
        cookieMap.put(c1.getId(), c1);
        cookieMap.put(c2.getId(), c2);
        cookieMap.put(c3.getId(), c3);

        return Stream.of(
                Arguments.of((Object) cookieMap)
        );

    }

    @ParameterizedTest
    @MethodSource(value =  "cookieMap")
    void testActiveCookiesByDate(Map<String, Cookie> cookieMap){

        List<String> expected = Arrays.asList("SAZuXPGUrfbcn5UA");

        CookieService cookieService = new CookieService();

        assertThat(cookieService.getActiveCookiesByDate(cookieMap,"2018-12-09" )).isEqualTo(expected);



    }

    @ParameterizedTest
    @MethodSource(value =  "cookieMap")
    void shouldReturnListActiveCookiesByDate(Map<String, Cookie> cookieMap){

        CookieService cookieService = new CookieService();

        List<String> expected = Arrays.asList("AtY0laUfhglK3lC7","5UAVanZf6UtGyKVS");
        assertThat(cookieService.getActiveCookiesByDate(cookieMap,"2018-12-10" ).size()).isEqualTo(2);
        assertThat(cookieService.getActiveCookiesByDate(cookieMap,"2018-12-10" )).isEqualTo(expected);


    }

    @ParameterizedTest
    @MethodSource(value =  "cookieMap")
    void shouldReturnEmptyCookiesforNotExistedDate(Map<String, Cookie> cookieMap){

        List<String> expected = new ArrayList<>();
        CookieService cookieService = new CookieService();

        assertThat(cookieService.getActiveCookiesByDate(cookieMap,"2018-12-18" )).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource(value =  "cookieMap")
    void shouldThrowExceptionInvalidDateFormat(Map<String, Cookie> cookieMap){

        CookieService cookieService = new CookieService();

        String invalidDate = "2018-xx-18";

        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            cookieService.getActiveCookiesByDate(cookieMap, invalidDate);
        });


        assertThat(exception.getErrorCode()).isEqualTo(Errors.INVALID_DATE_FORMAT.getCode());
        assertThat(exception.getErrorMessage()).isEqualTo(String.format(Errors.INVALID_DATE_FORMAT.getMessage(),invalidDate,ApplicationConstants.INPUT_DATE_FORMAT));

    }

}
