package com.aimprosoft.sandbox.service;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.domain.Department;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface DepartmentService {

    ArrayList<Department> getAllDepartments() throws IOException, SQLException;

    boolean checkDepartment(DepartmentData department) throws IOException, SQLException;

    void createDepartment(DepartmentData department) throws IOException, SQLException;

    void deleteDepartmentById(String id) throws IOException, SQLException;

    void updateDepartment(DepartmentData department) throws IOException, SQLException;

}
