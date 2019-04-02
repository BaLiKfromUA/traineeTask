package com.aimprosoft.sandbox.dao.impl;

import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.util.database.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BaLiK on 29.03.19
 */
public class HibernateEmployeeRepoImpl implements EmployeeRepo {
    private static Logger LOG = Logger.getLogger(HibernateEmployeeRepoImpl.class);

    @Override
    public ArrayList<Employee> getAllByDepartmentId(Long id) throws DatabaseException {
        ArrayList<Employee> employees;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employees = new ArrayList<>(session
                    .createQuery("SELECT E FROM Employee E WHERE E.departmentID=:department_id order by E.id", Employee.class)
                    .setParameter("department_id", id)
                    .getResultList());
        } catch (Exception e) {
            LOG.error("Can not get Employees by Department ID\n" + e.getMessage());
            throw new DatabaseException("Fail to get employees!");
        }
        return employees;
    }

    @Override
    public boolean checkEmployee(Employee employee) throws DatabaseException {
        List<Employee> employees;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final String hql = "FROM Employee E WHERE E.id <> :employee_id AND E.email=:employee_email";
            Query q = session.createQuery(hql)
                    .setParameter("employee_id", employee.getID())
                    .setParameter("employee_email", employee.getEmail());

            employees = q.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not get Employees by Email\n" + e.getMessage());
            throw new DatabaseException("Fail to check employee!");
        }

        return employees.isEmpty();
    }

    @Override
    public void createEmployee(Employee employee) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not create Employee\n" + e.getMessage());
            throw new DatabaseException("Fail to add new employee!");
        }
    }

    @Override
    public void deleteEmployeeById(Long id) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final String hql = "DELETE FROM Employee " +
                    "WHERE id = :employee_id";
            Query q = session.createQuery(hql).setParameter("employee_id", id);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not delete Employee " + id + "\n" + e.getMessage());
            throw new DatabaseException("Fail to delete employee!");
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
            throw new DatabaseException("Fail to update employee!");
        }
    }
}
