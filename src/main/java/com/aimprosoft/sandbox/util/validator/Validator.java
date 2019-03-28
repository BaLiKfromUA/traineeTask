package com.aimprosoft.sandbox.util.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BaLiK on 28.03.19
 */
public final class Validator {
    private static Pattern pattern;
    private static Matcher matcher;


    private static final String LOGIN_PATTERN = "^[a-z0-9_-]{5,21}$";
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{5,41}$";
    private static final String NUMBER_PATTERN = "\\d+";
    private static final String NAME_PATTERN = "^[A-Z][a-zA-Z]{5,21}$";

    public static boolean validateLogin(final String login) {
        if (login == null) {
            return false;
        }

        pattern = Pattern.compile(LOGIN_PATTERN);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public static boolean validateEmail(final String email) {
        if (email == null) {
            return false;
        }

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateRank(final String rankString) {
        if (rankString == null) {
            return false;
        }

        pattern = Pattern.compile(NUMBER_PATTERN);
        matcher = pattern.matcher(rankString);
        if (!matcher.matches()) {
            return false;
        }
        long rank = Long.parseLong(rankString);
        return rank > 0 && rank <= 1000;
    }

    //todo:better
    public static boolean validateDate(final String dateString) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateString);

            return (date.compareTo(format.parse("1979-12-31")) > 0) && (date.compareTo(format.parse("2079-12-31")) < 0);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateName(final String name) {
        if (name == null) {
            return false;
        }
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
