package com.chatopbackend.chatopbackend.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * Converts a Date object to a String formatted as "yyyy/MM/dd".
     *
     * @param date the Date object to convert
     * @return the formatted date string
     */
    public static String convertDateToString(Date date) {
        String pattern = "yyyy/MM/dd";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
