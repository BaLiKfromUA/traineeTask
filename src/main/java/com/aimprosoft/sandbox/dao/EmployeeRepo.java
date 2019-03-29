package com.aimprosoft.sandbox.dao;

import com.aimprosoft.sandbox.domain.Employee;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface EmployeeRepo {

    ArrayList<Employee> getAllByDepartmentId(Long id);

    boolean checkEmployee(Employee employee);

    void createEmployee(Employee employee);

    void deleteEmployeeById(Long id);

    void updateEmployee(Employee employee);

}
