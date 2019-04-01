package com.aimprosoft.sandbox.service;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.domain.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface EmployeeService {

    ArrayList<Employee> getAllByDepartmentId(String id) throws IOException, SQLException;

    boolean checkEmployee(EmployeeData employee) throws IOException, SQLException;

    void createEmployee(EmployeeData employee) throws IOException, SQLException;

    void deleteEmployeeById(String id) throws IOException, SQLException;

    void updateEmployee(EmployeeData employee) throws IOException, SQLException;

}
