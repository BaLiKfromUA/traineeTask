package com.aimprosoft.sandbox.dao;

import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.exception.DatabaseException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
@Repository
public interface EmployeeRepo {

    ArrayList<Employee> getAllByDepartmentId(Long id) throws DatabaseException;

    boolean checkEmployee(Employee employee) throws DatabaseException;

    void createEmployee(Employee employee) throws DatabaseException;

    void deleteEmployeeById(Long id) throws DatabaseException;

    void updateEmployee(Employee employee) throws DatabaseException;

}
