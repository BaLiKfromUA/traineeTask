package com.aimprosoft.sandbox.dao.impl.spring_orm;

import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.domain.Employee;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author BaLiK on 05.04.19
 */
@Repository
public class SpringOrmEmployeeRepoImpl implements EmployeeRepo {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<Employee> getAllByDepartmentId(Long id) {
        return new ArrayList<>(sessionFactory.getCurrentSession()
                .createQuery("SELECT E FROM Employee E where E.departmentID=? order by E.id")
                .setParameter(0, id)
                .list());
    }

    @Override
    public boolean checkEmployee(Employee employee) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee E where E.email=? and E.id!=?")
                .setParameter(0, employee.getEmail())
                .setParameter(1, employee.getID())
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
