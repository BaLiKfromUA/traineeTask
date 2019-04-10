package com.aimprosoft.sandbox.dao.impl.spring_orm;

import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.util.database.StatementsHQL;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author BaLiK on 05.04.19
 */
@Repository
@Transactional
public class SpringOrmEmployeeRepoImpl implements EmployeeRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ArrayList<Employee> getAllByDepartmentId(Long id) {
        return new ArrayList<>(sessionFactory.getCurrentSession()
                .createQuery(StatementsHQL.GET_ALL_EMPLOYEES)
                .setParameter("department_id", id)
                .list());
    }

    @Override
    public boolean checkEmployee(Employee employee) {
        return sessionFactory.getCurrentSession()
                .createQuery(StatementsHQL.CHECK_EMPLOYEE)
                .setParameter("employee_id", employee.getID())
                .setParameter("employee_email", employee.getEmail())
                .list().isEmpty();
    }

    @Override
    public void createEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = sessionFactory.getCurrentSession().load(Employee.class, id);
        if (null != employee) {
            sessionFactory.getCurrentSession().delete(employee);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        sessionFactory.getCurrentSession().update(employee);
    }
}
