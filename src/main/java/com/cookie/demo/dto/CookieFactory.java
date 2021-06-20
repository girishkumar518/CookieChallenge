package com.cookie.demo.dto;
/*
 *  Created by Girish Kumar CH on 6/18/21
 *  Cookie objects are created here and maintained using cookieId
 *  using Factory design pattern to return created cookies
 *  It is Singleton object
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CookieFactory {

    private static final Logger LOG = LogManager.getLogger(CookieFactory.class);
    private static final CookieFactory cookieFactory = new CookieFactory();

    private final Map<String,Cookie> cookieMap;

    private CookieFactory(){
        LOG.debug("Creating instance of cookieFactory");
        cookieMap = new ConcurrentHashMap<>();

    }

    public static CookieFactory getInstance(){
        return cookieFactory;
    }

    public Map<String, Cookie> getCookieMap() {
        return cookieMap;
    }

    /*
      this method will create the Cookie object and store in map key as a cookieId , value as cookie object
      one object will be created for each cookie ID
     */
    public Cookie getCookie(String cookieId){

        LOG.debug("Creating cooking if not existed for cookie {}",cookieId);

        Cookie opCookie = cookieMap.getOrDefault(cookieId,new Cookie(cookieId));
        cookieMap.put(cookieId,opCookie);

        return opCookie;

    }

}
