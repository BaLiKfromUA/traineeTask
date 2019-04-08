package com.aimprosoft.sandbox.controller.data;

import com.aimprosoft.sandbox.util.validator.DataPatterns;
import com.aimprosoft.sandbox.util.validator.annotation.employee.UniqueEmployee;
import net.sf.oval.constraint.*;

/**
 * @author BaLiK on 29.03.19
 */
@UniqueEmployee
public class EmployeeData {

    @NotNull(message = "Id can not be null")
    @NotEmpty(message = "Id can not be empty")
    @MinLength(value = 1, message = "Id length should be >= 1")
    @javax.validation.constraints.Pattern(regexp = DataPatterns.ID_PATTERN,
            message = "Id should be a number >= 0")
    private final String id;

    @NotNull(message = "Login can not be null")
    @NotEmpty(message = "Login can not be empty")
    @MinLength(value = 6, message = "Login length should be >= 6")
    @Length(max = 20, message = "Login length should be <= 20")
    @javax.validation.constraints.Pattern(regexp = DataPatterns.LOGIN_PATTERN,
            message = "Login may consist of uppercase English letters, numbers, \"_\" and \"-\".")
    private final String login;

    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @MinLength(value = 6, message = "Email length should be >= 6")
    @Length(max = 40, message = "Email length should be <= 40")
    @javax.validation.constraints.Pattern(regexp = DataPatterns.EMAIL_PATTERN,
            message = "Email should be valid")
    private final String email;

    @NotNull(message = "Rank can not be null")
    @NotEmpty(message = "Rank can not be empty")
    @MinLength(value = 1, message = "Rank length should be >= 1")
    @Length(max = 3, message = "Rank length should be <= 3")
    @javax.validation.constraints.Pattern(regexp = DataPatterns.RANK_PATTERN,
            message = "Rank should be >= 1 and < 1000")
    private final String rank;

    @NotNull(message = "Date can not be null")
    @NotEmpty(message = "Date can not be empty")
    @DateRange(min = "1979-12-31", max = "2079-12-31", format = "yyyy-MM-dd",
            message = "Date format should be 'yyyy-MM-dd'.\n" +
                    "Date cannot be before 1979-12-31 or after 2079-12-31.")
    private final String date;

    @NotNull(message = "Department Id can not be null")
    @NotEmpty(message = "Department Id can not be empty")
    @MinLength(value = 1, message = "Department Id length should be >= 1")
    @javax.validation.constraints.Pattern(regexp = DataPatterns.ID_PATTERN,
            message = "Department Id should be a number >= 0")
    private final String departmentId;

    public EmployeeData(String login, String email, String rank, String date, String departmentId) {
        this.id = "0";
        this.login = login;
        this.email = email;
        this.rank = rank;
        this.date = date;
        this.departmentId = departmentId;
    }

    public EmployeeData(String id, String login, String email, String rank, String date, String departmentId) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.rank = rank;
        this.date = date;
        this.departmentId = departmentId;
    }


    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getRank() {
        return rank;
    }

    public String getDate() {
        return date;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
