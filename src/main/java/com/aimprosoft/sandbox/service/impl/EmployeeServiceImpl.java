package com.aimprosoft.sandbox.service.impl;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.dao.impl.HibernateEmployeeRepoImpl;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo repo;

    public EmployeeServiceImpl() {
        repo = new HibernateEmployeeRepoImpl();
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
    public ArrayList<Employee> getAllByDepartmentId(String id) throws IOException, SQLException {
        return repo.getAllByDepartmentId(Long.parseLong(id));
    }

    @Override
    public boolean checkEmployee(EmployeeData employee) throws IOException, SQLException {
        return repo.checkEmployee(convert(employee));
    }

    @Override
    public void createEmployee(EmployeeData employee) throws IOException, SQLException {
        repo.createEmployee(convert(employee));
    }

    @Override
    public void deleteEmployeeById(String id) throws IOException, SQLException {
        repo.deleteEmployeeById(Long.parseLong(id));
    }

    @Override
    public void updateEmployee(EmployeeData employee) throws IOException, SQLException {
        repo.updateEmployee(convert(employee));
    }

}
