package com.chatopbackend.chatopbackend.utils;

import java.util.Optional;

public class Utils {
    private Utils(){}

    /**
     * Checks if a string represents a numeric value.
     *
     * @param strNum the string to check
     * @return true if the string is numeric, false otherwise
     */
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
     * Converts a string representation of a numeric value to a Float.
     *
     * @param data the string to convert
     * @return the Float value represented by the string, or 0f if the string is null
     */
    public static Float convertToNumeric(String data){
      return Optional.ofNullable(data).map(Float::parseFloat).orElse(0f);
    }

    /**
     * Checks if a string is not empty.
     *
     * @param value the string to check
     * @return true if the string is not empty, false otherwise
     */
    public static boolean isStringNotEmpty(String value) {
        return !value.isEmpty();
    }
}
