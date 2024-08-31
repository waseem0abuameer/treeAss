package com.example.treeassignment.util;

/**
 * The type String utils.
 */
public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Mask string string.
     *
     * @param input the input
     * @return the string
     */
    public static String maskString(String input) {
        if (input == null) {
            return "NULL";
        }
        return input.replaceAll(".(?=.{4})", "x");
    }
}