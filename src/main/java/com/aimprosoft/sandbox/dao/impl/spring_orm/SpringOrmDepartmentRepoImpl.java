package com.aimprosoft.sandbox.dao.impl.spring_orm;

import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.domain.Department;
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
                .createQuery("SELECT D FROM Department D order by D.id")
                .list());
    }

    @Override
    public boolean checkDepartment(Department department) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Department D where D.name=? and D.id!=?")
                .setParameter(0, department.getName())
                .setParameter(1, department.getID())
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
