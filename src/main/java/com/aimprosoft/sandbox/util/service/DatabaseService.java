package com.aimprosoft.sandbox.util.service;

import com.aimprosoft.sandbox.service.DepartmentService;
import com.aimprosoft.sandbox.service.EmployeeService;
import com.aimprosoft.sandbox.service.impl.DepartmentServiceImpl;
import com.aimprosoft.sandbox.service.impl.EmployeeServiceImpl;

/**
 * @author BaLiK on 29.03.19
 */
public class DatabaseService {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    private static DatabaseService instance;

    private DatabaseService() {
        employeeService = new EmployeeServiceImpl();
        departmentService = new DepartmentServiceImpl();
    }

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }

        return instance;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }
}
