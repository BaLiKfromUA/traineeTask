package com.aimprosoft.sandbox.database.department;

import java.util.ArrayList;

/**
 * @author BaLiK on 25.03.19
 */
public class Department {
    private String name;
    private ArrayList<String> employeeList;
    private Integer size;

    public Department() {
        size=0;
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
