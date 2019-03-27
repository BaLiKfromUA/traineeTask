package com.aimprosoft.sandbox.action;

import com.aimprosoft.sandbox.action.get.DepartmentsPage;
import com.aimprosoft.sandbox.action.get.EmployeesPage;
import com.aimprosoft.sandbox.action.post.department.AddNewDepartment;
import com.aimprosoft.sandbox.action.post.department.DeleteDepartment;
import com.aimprosoft.sandbox.action.post.department.EditDepartment;
import com.aimprosoft.sandbox.action.post.employee.AddNewEmployee;
import com.aimprosoft.sandbox.action.post.employee.DeleteEmployee;
import com.aimprosoft.sandbox.action.post.employee.EditEmployee;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BaLiK on 27.03.19
 */
public class ActionManager {
    private Map<String, Action> actions;

    public ActionManager() {
        actions = new HashMap<>();

        /**get**/
        actions.put("default", new DepartmentsPage());
        actions.put("employees", new EmployeesPage());

        /**post**/
        actions.put("employee edit", new EditEmployee());
        actions.put("add new employee", new AddNewEmployee());
        actions.put("employee delete", new DeleteEmployee());

        actions.put("department edit", new EditDepartment());
        actions.put("add new department", new AddNewDepartment());
        actions.put("department delete", new DeleteDepartment());
    }


    public Action getAction(String tag) {
        return actions.get(tag);
    }
}
