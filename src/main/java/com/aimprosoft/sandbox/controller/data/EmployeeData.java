package com.aimprosoft.sandbox.controller.data;

/**
 * @author BaLiK on 29.03.19
 */
public final class EmployeeData {
    private final String id;
    private final String login;
    private final String email;
    private final String rank;
    private final String date;
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
