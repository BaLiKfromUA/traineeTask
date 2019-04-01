package com.aimprosoft.sandbox.controller.data;

import com.aimprosoft.sandbox.util.validator.DataPatterns;
import net.sf.oval.constraint.*;

/**
 * @author BaLiK on 29.03.19
 */
public class EmployeeData {
    @NotNull
    @NotEmpty
    @MinLength(value = 1)
    @javax.validation.constraints.Pattern(regexp = DataPatterns.ID_PATTERN)
    private final String id;

    @NotNull
    @NotEmpty
    @MinLength(value = 6)
    @Length(max = 20)
    @javax.validation.constraints.Pattern(regexp = DataPatterns.LOGIN_PATTERN)
    private final String login;

    @NotNull
    @NotEmpty
    @MinLength(value = 6)
    @Length(max = 40)
    @javax.validation.constraints.Pattern(regexp = DataPatterns.EMAIL_PATTERN)
    private final String email;

    @NotNull
    @NotEmpty
    @MinLength(value = 1)
    @Length(max = 3)
    @javax.validation.constraints.Pattern(regexp = DataPatterns.RANK_PATTERN)
    private final String rank;

    @NotNull
    @NotEmpty
    @DateRange(min = "1979-12-31", max = "2079-12-31", format = "yyyy-MM-dd")
    private final String date;

    @NotNull
    @NotEmpty
    @MinLength(value = 1)
    @javax.validation.constraints.Pattern(regexp = DataPatterns.ID_PATTERN)
    private final String departmentId;

    public EmployeeData(String login, String email, String rank, String date, String departmentId) {
        this.id = "1";
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
