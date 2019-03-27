package com.aimprosoft.sandbox.actions;

import com.aimprosoft.sandbox.actions.get.DepartmentsPage;
import com.aimprosoft.sandbox.actions.get.EmployeesPage;
import com.aimprosoft.sandbox.actions.post.department.AddNewDepartment;
import com.aimprosoft.sandbox.actions.post.department.DeleteDepartment;
import com.aimprosoft.sandbox.actions.post.department.EditDepartment;
import com.aimprosoft.sandbox.actions.post.employee.DeleteEmployee;

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
        actions.put("employee delete", new DeleteEmployee());

        actions.put("department edit", new EditDepartment());
        actions.put("add new department", new AddNewDepartment());
        actions.put("department delete", new DeleteDepartment());
    }


    public Action getAction(String tag) {
        return actions.get(tag);
    }
}
