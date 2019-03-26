package com.aimprosoft.sandbox.database.department;

import java.util.ArrayList;

/**
 * @author BaLiK on 25.03.19
 */
public class Department {
    private Long ID;
    private String name;
    private ArrayList<String> employeeList;
    private Integer size;

    public Department(Long id) {
        size=0;
        ID=id;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<String> employeeList) {
        this.employeeList = employeeList;
        this.size=employeeList.size();
    }

    public Integer getSize() {
        return size;
    }
}
