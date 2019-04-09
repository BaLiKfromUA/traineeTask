package com.aimprosoft.sandbox.dao.impl.spring_orm;

import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.util.database.StatementsHQL;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author BaLiK on 05.04.19
 */
@Repository
public class SpringOrmDepartmentRepoImpl implements DepartmentRepo {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<Department> getAllDepartments() {
        return new ArrayList<>(sessionFactory.getCurrentSession()
                .createQuery(StatementsHQL.GET_ALL_DEPARTMENTS)
                .list());
    }

    @Override
    public boolean checkDepartment(Department department) {
        return sessionFactory.getCurrentSession()
                .createQuery(StatementsHQL.CHECK_DEPARTMENT)
                .setParameter("department_id", department.getID())
                .setParameter("department_name", department.getName())
                .list().isEmpty();
    }

    @Override
    public void createDepartment(Department department) {
        sessionFactory.getCurrentSession().save(department);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        Department department = sessionFactory.getCurrentSession().load(Department.class, id);
        if (null != department) {
            sessionFactory.getCurrentSession().delete(department);
        }
    }

    @Override
    public void updateDepartment(Department department) {
        sessionFactory.getCurrentSession().update(department);
    }
}
