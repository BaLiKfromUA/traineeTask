package com.aimprosoft.sandbox.service;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.domain.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface EmployeeService {

    ArrayList<Employee> getAllByDepartmentId(String id);

    boolean checkEmployee(EmployeeData employee);

    void createEmployee(EmployeeData employee);

    void deleteEmployeeById(String id);

    void updateEmployee(EmployeeData employee);

    void closeConnection() throws SQLException;

}
