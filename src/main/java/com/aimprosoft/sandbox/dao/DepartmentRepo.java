package com.aimprosoft.sandbox.dao;

import com.aimprosoft.sandbox.domain.Department;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface DepartmentRepo {

    ArrayList<Department> getAllDepartments();

    boolean checkDepartment(Department department);

    void createDepartment(Department department);

    void deleteDepartmentById(Long id);

    void updateDepartment(Department department);
}
