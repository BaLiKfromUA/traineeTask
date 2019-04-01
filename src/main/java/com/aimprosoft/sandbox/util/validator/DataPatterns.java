package com.aimprosoft.sandbox.util.validator;

/**
 * @author BaLiK on 01.04.19
 */
public class DataPatterns {
    public static final String LOGIN_PATTERN = "^[a-z0-9_-]{5,21}$";
    public static final String NAME_PATTERN = "^[A-Z][a-z]{5,21}$";
    public static final String EMAIL_PATTERN = "[a-z][a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
    public static final String ID_PATTERN = "^[1-9]\\d*$";
    public static final String RANK_PATTERN = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$";
    public static final String DATE_PATTERN="^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
}
