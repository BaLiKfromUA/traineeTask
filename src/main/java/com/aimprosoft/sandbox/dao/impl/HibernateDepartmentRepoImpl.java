package com.aimprosoft.sandbox.dao.impl;

import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.util.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BaLiK on 29.03.19
 */
//todo: message for user
public class HibernateDepartmentRepoImpl implements DepartmentRepo {
    @Override
    public ArrayList<Department> getAllDepartments() {
        ArrayList<Department> departments = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            departments = new ArrayList<>(session.createQuery("SELECT D FROM Department D order by D.id", Department.class).getResultList());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return departments;
    }

    @Override
    public boolean checkDepartment(Department department) {
        List<Department> departments = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final String hql = "FROM Department D WHERE D.id <> :department_id AND D.name=:department_name";
            Query q = session.createQuery(hql)
                    .setParameter("department_id", department.getID())
                    .setParameter("department_name", department.getName());

            departments = q.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return departments.isEmpty();
    }

    @Override
    public void createDepartment(Department department) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void deleteDepartmentById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final String hql = "DELETE FROM Department " +
                    "WHERE id = :department_id";
            Query q = session.createQuery(hql).setParameter("department_id", id);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void updateDepartment(Department department) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
