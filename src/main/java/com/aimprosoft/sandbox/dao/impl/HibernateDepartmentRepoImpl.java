package com.aimprosoft.sandbox.dao.impl;

import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.util.database.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BaLiK on 29.03.19
 */
public class HibernateDepartmentRepoImpl implements DepartmentRepo {
    private static Logger LOG = LogManager.getLogger(HibernateDepartmentRepoImpl.class);

    public HibernateDepartmentRepoImpl() {
    }

    @Override
    public ArrayList<Department> getAllDepartments() throws DatabaseException {
        ArrayList<Department> departments;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            departments = new ArrayList<>(session.createQuery("SELECT D FROM Department D order by D.id", Department.class).getResultList());
        } catch (Exception e) {
            LOG.error("Can not get Departments\n{}", e.getMessage());
            throw new DatabaseException("Fail to get departments!");
        }
        return departments;
    }

    @Override
    public boolean checkDepartment(Department department) throws DatabaseException {
        List<Department> departments;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final String hql = "FROM Department D WHERE D.id <> :department_id AND D.name=:department_name";
            Query q = session.createQuery(hql)
                    .setParameter("department_id", department.getID())
                    .setParameter("department_name", department.getName());

            departments = q.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not get Department by Name\n{}", e.getMessage());
            throw new DatabaseException("Fail to check department!");
        }

        return departments.isEmpty();
    }

    @Override
    public void createDepartment(Department department) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not create Department\n{}", e.getMessage());
            throw new DatabaseException("Fail to add new department!");
        }
    }

    @Override
    public void deleteDepartmentById(Long id) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final String hql = "DELETE FROM Department " +
                    "WHERE id = :department_id";
            Query q = session.createQuery(hql).setParameter("department_id", id);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not delete Department {}\n{}", id, e.getMessage());
            throw new DatabaseException("Fail to delete department!");
        }
    }

    @Override
    public void updateDepartment(Department department) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not update department\n{}", e.getMessage());
            throw new DatabaseException("Fail to edit department!");
        }
    }
}
