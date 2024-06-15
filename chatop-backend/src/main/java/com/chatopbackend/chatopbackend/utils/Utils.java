package com.chatopbackend.chatopbackend.utils;

import java.util.Optional;

public class Utils {
    private Utils(){}

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param data
     * @return
     */
    public static Float convertToNumeric(String data){
      return Optional.ofNullable(data).map(Float::parseFloat).orElse(0f);
    }
}
