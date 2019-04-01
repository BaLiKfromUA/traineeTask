package com.aimprosoft.sandbox.dao;

import com.aimprosoft.sandbox.domain.Department;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
public interface DepartmentRepo {

    ArrayList<Department> getAllDepartments() throws IOException, SQLException;

    boolean checkDepartment(Department department) throws IOException, SQLException;

    void createDepartment(Department department) throws IOException, SQLException;

    void deleteDepartmentById(Long id) throws IOException, SQLException;

    void updateDepartment(Department department) throws IOException, SQLException;
}
