package com.aimprosoft.sandbox.util.validator;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.domain.Department;

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
    private static final String EMAIL_PATTERN = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
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

    public static boolean validateUser(EmployeeData data) {
        return validateId(data.getId()) && validateLogin(data.getLogin())
                && validateEmail(data.getEmail()) && validateRank(data.getRank())
                && validateDate(data.getDate()) && validateId(data.getDepartmentId());
    }

    public static boolean validateDepartment(DepartmentData data) {
        return validateId(data.getId()) && validateName(data.getName());
    }

    public static boolean validateEmail(final String email) {
        if (email == null) {
            return false;
        }

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches() && email.length() > 5 && email.length() < 41;
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

    public static boolean validateId(final String idStr) {
        if (idStr == null) {
            return false;
        }
        pattern = Pattern.compile(NUMBER_PATTERN);
        matcher = pattern.matcher(idStr);
        if (!matcher.matches()) {
            return false;
        }
        long id = Long.parseLong(idStr);
        return id > 0;

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
