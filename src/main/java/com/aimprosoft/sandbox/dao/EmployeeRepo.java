package com.aimprosoft.sandbox.dao;

import com.aimprosoft.sandbox.domain.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface EmployeeRepo {

    ArrayList<Employee> getAllByDepartmentId(Long id) throws IOException, SQLException;

    boolean checkEmployee(Employee employee) throws IOException, SQLException;

    void createEmployee(Employee employee) throws IOException, SQLException;

    void deleteEmployeeById(Long id) throws IOException, SQLException;

    void updateEmployee(Employee employee) throws IOException, SQLException;

}
