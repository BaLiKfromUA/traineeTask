package com.aimprosoft.sandbox.config;

import com.aimprosoft.sandbox.controller.servlet.action.Action;
import com.aimprosoft.sandbox.controller.servlet.action.ActionManager;
import com.aimprosoft.sandbox.controller.servlet.action.get.DepartmentsPage;
import com.aimprosoft.sandbox.controller.servlet.action.get.EmployeesPage;
import com.aimprosoft.sandbox.controller.servlet.action.get.ErrorPage;
import com.aimprosoft.sandbox.controller.servlet.action.post.department.AddNewDepartment;
import com.aimprosoft.sandbox.controller.servlet.action.post.department.DeleteDepartment;
import com.aimprosoft.sandbox.controller.servlet.action.post.department.EditDepartment;
import com.aimprosoft.sandbox.controller.servlet.action.post.employee.AddNewEmployee;
import com.aimprosoft.sandbox.controller.servlet.action.post.employee.DeleteEmployee;
import com.aimprosoft.sandbox.controller.servlet.action.post.employee.EditEmployee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BaLiK on 12.04.19
 */
@Configuration
public class ActionConfig {
    /**
     * ACTIONS
     **/
    @Bean
    public DepartmentsPage getDepartments() {
        return new DepartmentsPage();
    }

    @Bean
    public EmployeesPage getEmployees() {
        return new EmployeesPage();
    }

    @Bean
    public ErrorPage error() {
        return new ErrorPage();
    }

    @Bean
    public AddNewDepartment addDepartment() {
        return new AddNewDepartment();
    }

    @Bean
    public DeleteDepartment deleteDepartment() {
        return new DeleteDepartment();
    }

    @Bean
    public EditDepartment editDepartment() {
        return new EditDepartment();
    }

    @Bean
    public AddNewEmployee addEmployee() {
        return new AddNewEmployee();
    }

    @Bean
    public DeleteEmployee deleteEmployee() {
        return new DeleteEmployee();
    }

    @Bean
    public EditEmployee editEmployee() {
        return new EditEmployee();
    }

    /**
     * ACTION MAP
     **/
    @Bean
    public Map<String, Action> actionMap() {
        Map<String, Action> actionMap = new HashMap<>();

        actionMap.put("/departments", getDepartments());
        actionMap.put("/employees", getEmployees());
        actionMap.put("/error", error());

        actionMap.put("/departments/add", addDepartment());
        actionMap.put("/departments/delete", deleteDepartment());
        actionMap.put("/departments/edit", editDepartment());

        actionMap.put("/employees/add", addEmployee());
        actionMap.put("/employees/delete", deleteEmployee());
        actionMap.put("/employees/edit", editEmployee());

        return actionMap;
    }

    /**
     * ACTION MANAGER
     **/
    @Bean
    public ActionManager actionManager() {
        ActionManager actionManager = new ActionManager();
        actionManager.setActions(actionMap());
        return actionManager;
    }

}
