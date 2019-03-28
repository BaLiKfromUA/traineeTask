package com.aimprosoft.sandbox.database.employee;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaLiK on 25.03.19
 */
public class Employee {
    @Min(value = 0, message = "ID should be greater than -1")
    private final Long ID;

    @Size(min = 6, max = 512, message = "Login should be between 6 - 512 characters.")
    private String login;

    @Size(min = 6, max = 512, message = "Email should be between 6 - 512 characters.")
    //todo:validate pattern
    private String email;

    @Max(value = 1000, message = "Rank should be less than 1001")
    @Min(value = 1, message = "Rank should be greater than 0")
    private Integer rank;

    //todo:validate pattern
    private Date registrationDate;//todo: change to long(with pattern)

    @Min(value = 0, message = "ID should be greater than -1")
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
