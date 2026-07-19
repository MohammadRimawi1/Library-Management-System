package com.exalt.library.validation;

/**
 * a class containing simple, reusable validation checks
 * that can be called manually wherever needed
 * @author Mohammad Rimawi
 */
public class Validator {
    /**
     * a private constructor to prevent instantiation
     */
    private Validator() {}

    /**
     * checks that a string is not null and not just whitespace
     * @param value
     * @return
     */
    public static boolean notBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * checks that an object is not null
     * @param value
     * @return
     */
    public static boolean notNull(Object value) {
        return value != null;
    }

    /**
     * checks that a string's length is within the given bounds
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static boolean size(String value, int min, int max) {
        if (value == null) return false;
        int length = value.trim().length();
        return length >= min && length <= max;
    }

    /**
     * checks that an int is within the given bounds
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static boolean between(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * checks that a string matches the given regex pattern
     * @param value
     * @param regex
     * @return
     */
    public static boolean matches(String value, String regex) {
        return value != null && value.matches(regex);
    }

    /**
     * checks that a string looks like a valid MongoDB ObjectId
     * @param value
     * @return
     */
    public static boolean isValidObjectId(String value) {
        return matches(value, "^[a-fA-F0-9]{24}$");
    }

    /**
     * checks that a string is one of the allowed library item types
     * @param value
     * @return
     */
    public static boolean isValidItemType(String value) {
        return matches(value, "BookPhysical|StoryPhysical|BookOnline|StoryOnline");
    }
}