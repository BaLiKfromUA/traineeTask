package com.aimprosoft.sandbox.dao.impl;

import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.util.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BaLiK on 29.03.19
 */
public class HibernateEmployeeRepoImpl implements EmployeeRepo {
    @Override
    public ArrayList<Employee> getAllByDepartmentId(Long id) {
        ArrayList<Employee> employees = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employees = new ArrayList<>(session
                    .createQuery("SELECT E FROM Employee E WHERE E.departmentID=:department_id order by E.id", Employee.class)
                    .setParameter("department_id", id)
                    .getResultList());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return employees;
    }

    //todo: case sensitive
    @Override
    public boolean checkEmployee(Employee employee) {
        List<Employee> employees = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final String hql = "FROM Employee E WHERE E.id <> :employee_id AND E.email=:employee_email";
            Query q = session.createQuery(hql)
                    .setParameter("employee_id", employee.getID())
                    .setParameter("employee_email", employee.getEmail());

            employees = q.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return employees.isEmpty();
    }

    @Override
    public void createEmployee(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final String hql = "DELETE FROM Employee " +
                    "WHERE id = :employee_id";
            Query q = session.createQuery(hql).setParameter("employee_id", id);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
