package com.aimprosoft.sandbox.domain;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaLiK on 25.03.19
 */
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "department_id")
    private Long departmentID;

    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
    private Department department;

    @Transient
    private String dateString;

    @Transient
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Employee(Long id) {
        ID = id;
    }

    public Employee() {
        ID = 0L;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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
            this.dateString = date;
            this.registrationDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDateString() {
        dateString = format.format(registrationDate);
        return dateString;
    }

    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
