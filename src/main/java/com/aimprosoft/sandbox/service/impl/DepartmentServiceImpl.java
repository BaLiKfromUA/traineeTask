package com.aimprosoft.sandbox.service.impl;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.service.DepartmentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepo repo;

    public DepartmentServiceImpl() throws IOException, SQLException {
        repo = new DepartmentDAO();
    }

    private Department convert(DepartmentData departmentData) {
        Department department = new Department();
        department.setID(Long.parseLong(departmentData.getId()));
        department.setName(departmentData.getName());

        return department;
    }

    @Override
    public ArrayList<Department> getAllDepartments() {
        return repo.getAllDepartments();
    }

    @Override
    public boolean checkDepartment(DepartmentData department) {
        return repo.checkDepartment(convert(department));
    }

    @Override
    public void createDepartment(DepartmentData department) {
        repo.createDepartment(convert(department));
    }

    @Override
    public void deleteDepartmentById(String id) {
        repo.deleteDepartmentById(Long.parseLong(id));
    }

    @Override
    public void updateDepartment(DepartmentData department) {
        repo.updateDepartment(convert(department));
    }

    @Override
    public void closeConnection() throws SQLException {
        repo.closeConnection();
    }
}
