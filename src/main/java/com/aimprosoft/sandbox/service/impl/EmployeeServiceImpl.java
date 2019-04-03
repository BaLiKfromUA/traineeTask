package com.aimprosoft.sandbox.service.impl;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo repo;

    public EmployeeServiceImpl() {
    }

    public void setRepo(EmployeeRepo repo) {
        this.repo = repo;
    }

    private Employee convert(EmployeeData data) {
        Employee employee = new Employee();
        employee.setID(Long.parseLong(data.getId()));
        employee.setLogin(data.getLogin());
        employee.setEmail(data.getEmail());
        employee.setRank(Integer.parseInt(data.getRank()));
        employee.setRegistrationDate(data.getDate());
        employee.setDepartmentID(Long.parseLong(data.getDepartmentId()));

        return employee;
    }

    @Override
    public ArrayList<Employee> getAllByDepartmentId(String id) throws DatabaseException {
        return repo.getAllByDepartmentId(Long.parseLong(id));
    }

    @Override
    public boolean checkEmployee(EmployeeData employee) throws DatabaseException {
        return repo.checkEmployee(convert(employee));
    }

    @Override
    public void createEmployee(EmployeeData employee) throws DatabaseException {
        repo.createEmployee(convert(employee));
    }

    @Override
    public void deleteEmployeeById(String id) throws DatabaseException {
        repo.deleteEmployeeById(Long.parseLong(id));
    }

    @Override
    public void updateEmployee(EmployeeData employee) throws DatabaseException {
        repo.updateEmployee(convert(employee));
    }
}
