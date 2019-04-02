package com.aimprosoft.sandbox.service;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.exception.DatabaseException;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface DepartmentService {

    ArrayList<Department> getAllDepartments() throws DatabaseException;

    boolean checkDepartment(DepartmentData department) throws DatabaseException;

    void createDepartment(DepartmentData department) throws DatabaseException;

    void deleteDepartmentById(String id) throws DatabaseException;

    void updateDepartment(DepartmentData department) throws DatabaseException;

}
