package com.chatopbackend.chatopbackend.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String convertDateToString(Date date) {
        String pattern = "yyyy/dd/MM";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String generateStringFromDate(String ext){
        return new SimpleDateFormat("yyyyMMddhhmmss'."+ext+"'").format(new Date());
    }
}
