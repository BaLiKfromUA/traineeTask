package com.aimprosoft.sandbox.service.impl;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;
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

    public EmployeeServiceImpl() throws IOException, SQLException {
        repo = new EmployeeDAO();
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
    public ArrayList<Employee> getAllByDepartmentId(String id) {
        return repo.getAllByDepartmentId(Long.parseLong(id));
    }

    @Override
    public boolean checkEmployee(EmployeeData employee) {
        return repo.checkEmployee(convert(employee));
    }

    @Override
    public void createEmployee(EmployeeData employee) {
        repo.createEmployee(convert(employee));
    }

    @Override
    public void deleteEmployeeById(String id) {
        repo.deleteEmployeeById(Long.parseLong(id));
    }

    @Override
    public void updateEmployee(EmployeeData employee) {
        repo.updateEmployee(convert(employee));
    }

    @Override
    public void closeConnection() throws SQLException {
        repo.closeConnection();
    }
}
