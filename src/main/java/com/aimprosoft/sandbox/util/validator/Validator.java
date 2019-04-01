package com.aimprosoft.sandbox.util.validator;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.controller.data.EmployeeData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BaLiK on 28.03.19
 */
public final class Validator {
    private static Pattern pattern;
    private static Matcher matcher;

    static boolean validateLogin(final String login) {
        if (login == null) {
            return false;
        }

        pattern = Pattern.compile(DataPatterns.LOGIN_PATTERN);
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

        pattern = Pattern.compile(DataPatterns.EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches() && email.length() > 5 && email.length() < 41;
    }

    static boolean validateRank(final String rankString) {
        if (rankString == null) {
            return false;
        }

        pattern = Pattern.compile(DataPatterns.RANK_PATTERN);
        matcher = pattern.matcher(rankString);

        return matcher.matches();
    }

    public static boolean validateId(final String idStr) {
        if (idStr == null) {
            return false;
        }
        pattern = Pattern.compile(DataPatterns.ID_PATTERN);
        matcher = pattern.matcher(idStr);

        return matcher.matches();
    }

    static boolean validateDate(final String dateString) {
        if(dateString==null){
            return false;
        }
        pattern=Pattern.compile(DataPatterns.DATE_PATTERN);
        matcher=pattern.matcher(dateString);

        return matcher.matches();
    }

    public static boolean validateName(final String name) {
        if (name == null) {
            return false;
        }
        pattern = Pattern.compile(DataPatterns.NAME_PATTERN);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
