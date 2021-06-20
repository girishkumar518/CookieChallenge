package com.cookie.demo.parsers;
/*
 *  Created by Girish Kumar CH on 6/18/21
 */

import com.cookie.demo.constants.ApplicationConstants;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserFactoryTest {


    @Test
    void successForValidFileType(){

        ParserFactory factory = ParserFactory.getInstance();

        assertThat(factory.getParser(ApplicationConstants.FORMAT_CSV)).isNotNull();

    }

    @Test
    void failureForNonValidFileType(){

        ParserFactory factory = ParserFactory.getInstance();

        assertThat(factory.getParser("dummy")).isNull();

    }

}
