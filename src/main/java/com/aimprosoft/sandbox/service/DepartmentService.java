package com.aimprosoft.sandbox.service;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.domain.Department;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface DepartmentService {

    ArrayList<Department> getAllDepartments();

    boolean checkDepartment(DepartmentData department);

    void createDepartment(DepartmentData department);

    void deleteDepartmentById(String id);

    void updateDepartment(DepartmentData department);

    void closeConnection() throws SQLException;

}
