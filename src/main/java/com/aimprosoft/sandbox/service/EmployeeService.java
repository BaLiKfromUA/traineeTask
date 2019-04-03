package com.aimprosoft.sandbox.service;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.exception.DatabaseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
@Service
public interface EmployeeService {

    ArrayList<Employee> getAllByDepartmentId(String id) throws DatabaseException;

    boolean checkEmployee(EmployeeData employee) throws DatabaseException;

    void createEmployee(EmployeeData employee) throws DatabaseException;

    void deleteEmployeeById(String id) throws DatabaseException;

    void updateEmployee(EmployeeData employee) throws DatabaseException;

}
