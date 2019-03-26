package com.aimprosoft.sandbox.database.employee;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaLiK on 25.03.19
 */
public class Employee {
    private final Long ID;
    private String login;
    private String email;
    private Integer rank;
    private Date registrationDate;//todo: change to long(with pattern)
    private Long departmentID;
    private String dateString;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Employee(Long id) {
        ID = id;
    }

    public Long getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        dateString = format.format(registrationDate);
    }

    public void setRegistrationDate(String date) {
        try {
            this.registrationDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            this.registrationDate = new Date(currentTime.getTime());
        }
    }

    public String getDateString() {
        return dateString;
    }

    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }
}
