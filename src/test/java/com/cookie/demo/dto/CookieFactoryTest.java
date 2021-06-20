package com.cookie.demo.dto;
/*
 *  Created by Girish Kumar CH on 6/18/21
 */

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

public class CookieFactoryTest {

    @Test
    void shouldProduceCookie() throws IOException {

        CookieFactory factory = CookieFactory.getInstance();

        String cookieId1 = "dummy1";
        String cookieId2 = "dummy2";

        Cookie cookie = factory.getCookie(cookieId1);
        assertThat(cookie.getId()).isEqualTo(cookieId1);

        cookie = factory.getCookie(cookieId1);
        assertThat(cookie.getId()).isEqualTo(cookieId1);

        cookie = factory.getCookie(cookieId2);
        assertThat(cookie.getId()).isEqualTo(cookieId2);

        assertThat(factory.getCookieMap().size()).isEqualTo(2);

    }
}
