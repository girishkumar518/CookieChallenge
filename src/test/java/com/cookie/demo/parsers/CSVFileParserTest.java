package com.cookie.demo.parsers;
/*
 *  Created by Girish Kumar CH on 6/18/21
 */

import com.cookie.demo.CookieApplicationTest;
import com.cookie.demo.constants.ApplicationConstants;
import com.cookie.demo.constants.Errors;
import com.cookie.demo.dto.Cookie;
import com.cookie.demo.dto.CookieFactory;
import com.cookie.demo.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVFileParserTest {

    private static String getFilePath(String fileName){

        ClassLoader classLoader = CookieApplicationTest.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        return file.getAbsolutePath();

    }

    public static Stream<Arguments> cookieFilePath(){

        return Stream.of(
                Arguments.of((Object) getFilePath("cookie_log.csv"))
        );

    }

    public static Stream<Arguments> wrongCookieFilePath(){

        return Stream.of(
                Arguments.of((Object) getFilePath("cookie_log_wrong_format.csv"))
        );
    }

    public static Stream<Arguments> blankCookieFilePath(){

        return Stream.of(
                Arguments.of((Object) getFilePath("cookie_log_blank.csv"))
        );

    }

    public static Stream<Arguments> blankCookieWithHeaderFilePath(){

        return Stream.of(
                Arguments.of((Object) getFilePath("cookie_log_blank_headers.csv"))
        );

    }

    @BeforeEach
    void cleanAllCookies(){
        CookieFactory.getInstance().getCookieMap().clear();
    }

    @ParameterizedTest
    @MethodSource(value =  "cookieFilePath")
    void shouldSuccessWithOutExceptions(String filePath) throws IOException {

        Parser csvParser = ParserFactory.getInstance().getParser(ApplicationConstants.FORMAT_CSV);

        Map<String, Cookie> returnOp = csvParser.getParsedCookies(filePath);

        assertThat(returnOp.size()).isEqualTo(5);

    }

    @ParameterizedTest
    @MethodSource(value =  "cookieFilePath")
    void checkForOccurenceTimeForCookie(String filePath) throws IOException {

        Parser csvParser = ParserFactory.getInstance().getParser(ApplicationConstants.FORMAT_CSV);

        Map<String, Cookie> returnOp = csvParser.getParsedCookies(filePath);

        String cookie = "AtY0laUfhglK3lC7";

        assertThat(returnOp.get(cookie).getOccuredTimes().size()).isEqualTo(3);

    }

    @ParameterizedTest
    @MethodSource(value =  "wrongCookieFilePath")
    void checkForOccurenceTimeForCookieIfDateFormatInvalid(String filePath) throws IOException {

        Parser csvParser = ParserFactory.getInstance().getParser(ApplicationConstants.FORMAT_CSV);

        Map<String, Cookie> returnOp = csvParser.getParsedCookies(filePath);

        String cookie = "AtY0laUfhglK3lC7";

        assertThat(returnOp.get(cookie).getOccuredTimes().size()).isEqualTo(2);

    }

    @ParameterizedTest
    @MethodSource(value =  "blankCookieFilePath")
    void throwExceptionforBlankCSVFile(String filePath) throws IOException {

        Parser csvParser = ParserFactory.getInstance().getParser(ApplicationConstants.FORMAT_CSV);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            csvParser.getParsedCookies(filePath);
        });

        String expected = "Index for header 'timestamp' is 1 but CSVRecord only has 1 values!";
        String actual = exception.getMessage();

        assertThat(actual).isEqualTo(expected);

    }


    @ParameterizedTest
    @MethodSource(value =  "blankCookieWithHeaderFilePath")
    void successforBlankWithHeaderCSVFile(String filePath) throws IOException {

        Parser csvParser = ParserFactory.getInstance().getParser(ApplicationConstants.FORMAT_CSV);
        Map<String, Cookie> returnOp = csvParser.getParsedCookies(filePath);

        assertThat(returnOp.size()).isEqualTo(0);

    }


    @ParameterizedTest
    @MethodSource(value =  "cookieFilePath")
    void shouldReturnTrueforValidate(String filePath) throws IOException {

        Parser csvParser = ParserFactory.getInstance().getParser(ApplicationConstants.FORMAT_CSV);

        assertThat(csvParser.validate(filePath)).isEqualTo(true);

    }

    @Test
    void shouldThorwApplicationExceptionforValidateWrongPath() throws IOException {

        String filePath = "dummy";

        Parser csvParser = ParserFactory.getInstance().getParser(ApplicationConstants.FORMAT_CSV);

        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            csvParser.validate(filePath);
        });

        assertThat(exception.getErrorCode()).isEqualTo(Errors.FILE_NOT_EXISTED.getCode());
        assertThat(exception.getMessage()).isEqualTo(String.format(Errors.FILE_NOT_EXISTED.getMessage(),filePath));

    }


}
