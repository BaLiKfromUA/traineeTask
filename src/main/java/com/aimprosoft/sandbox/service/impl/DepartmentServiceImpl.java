package com.aimprosoft.sandbox.service.impl;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.dao.impl.HibernateDepartmentRepoImpl;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.DepartmentService;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepo repo;

    public DepartmentServiceImpl() {
        repo = new HibernateDepartmentRepoImpl();
    }

    private Department convert(DepartmentData departmentData) {
        Department department = new Department();
        department.setID(Long.parseLong(departmentData.getId()));
        department.setName(departmentData.getName());

        return department;
    }

    @Override
    public ArrayList<Department> getAllDepartments() throws DatabaseException {
        return repo.getAllDepartments();
    }

    @Override
    public boolean checkDepartment(DepartmentData department) throws DatabaseException {
        return repo.checkDepartment(convert(department));
    }

    @Override
    public void createDepartment(DepartmentData department) throws DatabaseException {
        repo.createDepartment(convert(department));
    }

    @Override
    public void deleteDepartmentById(String id) throws DatabaseException {
        repo.deleteDepartmentById(Long.parseLong(id));
    }

    @Override
    public void updateDepartment(DepartmentData department) throws DatabaseException {
        repo.updateDepartment(convert(department));
    }

}
