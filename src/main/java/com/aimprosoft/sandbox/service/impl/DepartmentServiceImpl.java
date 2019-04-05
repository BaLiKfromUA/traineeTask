package com.aimprosoft.sandbox.service.impl;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOG = LogManager.getLogger(DepartmentServiceImpl.class);

    private DepartmentRepo repo;

    public DepartmentServiceImpl() {
    }

    public void setRepo(DepartmentRepo repo) {
        this.repo = repo;
        LOG.info("Department repo injected!");
    }

    private Department convert(DepartmentData departmentData) {
        Department department = new Department();
        department.setID(Long.parseLong(departmentData.getId()));
        department.setName(departmentData.getName());

        return department;
    }

    @Override
    @Transactional
    public ArrayList<Department> getAllDepartments() throws DatabaseException {
        return repo.getAllDepartments();
    }

    @Override
    @Transactional
    public boolean checkDepartment(DepartmentData department) throws DatabaseException {
        return repo.checkDepartment(convert(department));
    }

    @Override
    @Transactional
    public void createDepartment(DepartmentData department) throws DatabaseException {
        repo.createDepartment(convert(department));
    }

    @Override
    @Transactional
    public void deleteDepartmentById(String id) throws DatabaseException {
        repo.deleteDepartmentById(Long.parseLong(id));
    }

    @Override
    @Transactional
    public void updateDepartment(DepartmentData department) throws DatabaseException {
        repo.updateDepartment(convert(department));
    }
}
