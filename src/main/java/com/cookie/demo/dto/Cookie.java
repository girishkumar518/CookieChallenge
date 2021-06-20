package com.cookie.demo.dto;
/*
 *  Created by Girish Kumar CH on 6/17/21
 *  It is DTO object hold data of cookie with info of uniqueId and occured timeStamps
 */

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cookie {

    private final String id;
    private final List<DateTime> occuredTimes;

    public Cookie(String id) {
        this.id = id;
        this.occuredTimes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<DateTime> getOccuredTimes() {
        return occuredTimes;
    }

    public void addOccuredTime(DateTime dateAndTime) {

        if (Objects.nonNull(dateAndTime))
            this.occuredTimes.add(dateAndTime);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cookie cookie = (Cookie) o;

        return id != null ? id.equals(cookie.id) : cookie.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "id='" + id + '\'' +
                ", occuredTimes=" + occuredTimes +
                '}';
    }
}
