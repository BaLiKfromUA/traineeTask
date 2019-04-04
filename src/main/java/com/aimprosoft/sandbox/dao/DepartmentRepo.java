package com.aimprosoft.sandbox.dao;

import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.exception.DatabaseException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author BaLiK on 29.03.19
 */
@Repository
public interface DepartmentRepo {

    ArrayList<Department> getAllDepartments() throws DatabaseException;

    boolean checkDepartment(Department department) throws DatabaseException;

    void createDepartment(Department department) throws DatabaseException;

    void deleteDepartmentById(Long id) throws DatabaseException;

    void updateDepartment(Department department) throws DatabaseException;
}
