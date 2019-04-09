package com.aimprosoft.sandbox.util.database;

/**
 * @author BaLiK on 09.04.19
 */
public class StatementsHQL {
    public static final String GET_ALL_DEPARTMENTS = "SELECT D FROM Department D order by D.id";
    public static final String CHECK_DEPARTMENT = "FROM Department D WHERE D.id <> :department_id AND D.name=:department_name";
    public static final String DELETE_DEPARTMENT = "DELETE FROM Department WHERE id = :department_id";

    public static final String GET_ALL_EMPLOYEES = "SELECT E FROM Employee E WHERE E.departmentID=:department_id order by E.id";
    public static final String CHECK_EMPLOYEE="FROM Employee E WHERE E.id <> :employee_id AND E.email=:employee_email";
    public static final String DELETE_EMPLOYEE="DELETE FROM Employee WHERE id = :employee_id";
}
