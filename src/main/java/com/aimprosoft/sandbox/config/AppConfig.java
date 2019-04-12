package com.aimprosoft.sandbox.config;

import com.aimprosoft.sandbox.controller.servlet.action.Action;
import com.aimprosoft.sandbox.controller.servlet.action.ActionManager;
import com.aimprosoft.sandbox.controller.servlet.action.get.DepartmentsPage;
import com.aimprosoft.sandbox.controller.servlet.action.get.EmployeesPage;
import com.aimprosoft.sandbox.controller.servlet.action.get.ErrorPage;
import com.aimprosoft.sandbox.controller.servlet.action.post.department.AddNewDepartment;
import com.aimprosoft.sandbox.controller.servlet.action.post.department.DeleteDepartment;
import com.aimprosoft.sandbox.controller.servlet.action.post.department.EditDepartment;
import com.aimprosoft.sandbox.controller.servlet.action.post.employee.AddNewEmployee;
import com.aimprosoft.sandbox.controller.servlet.action.post.employee.DeleteEmployee;
import com.aimprosoft.sandbox.controller.servlet.action.post.employee.EditEmployee;
import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.dao.impl.hibernate.HibernateDepartmentRepoImpl;
import com.aimprosoft.sandbox.dao.impl.hibernate.HibernateEmployeeRepoImpl;
import com.aimprosoft.sandbox.dao.impl.jdbc.DepartmentRepoImpl;
import com.aimprosoft.sandbox.dao.impl.jdbc.EmployeeRepoImpl;
import com.aimprosoft.sandbox.dao.impl.spring_orm.SpringOrmDepartmentRepoImpl;
import com.aimprosoft.sandbox.dao.impl.spring_orm.SpringOrmEmployeeRepoImpl;
import com.aimprosoft.sandbox.service.impl.DepartmentServiceImpl;
import com.aimprosoft.sandbox.service.impl.EmployeeServiceImpl;
import com.aimprosoft.sandbox.util.database.DatabaseManager;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author BaLiK on 09.04.19
 */
//TODO: разделить на несколько классов
@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
@EnableScheduling //task:annotation-driven
@ImportResource("classpath:spring_config.xml")
public class AppConfig {

    @Autowired
    private Environment env;

    /**
     * DATABASE DATA
     **/
    @Value("${jdbc.driverClassName}")
    private String DRIVER;
    @Value("${database.url}")
    private String URL;
    @Value("${database.user}")
    private String USER;
    @Value("${database.password}")
    private String PASSWORD;

    /**
     * HIBERNATE CONFIG
     **/

    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.put("hibernate.dialect", env.getProperty("dialect"));
        hibernateProperties.put("hibernate.show_sql", env.getProperty("show_sql"));
        hibernateProperties.put("hibernate.format_sql", env.getProperty("format_sql"));
        hibernateProperties.put("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));
        hibernateProperties.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hbm2ddl.auto"));
        hibernateProperties.put("hibernate.connection.pool_size", env.getProperty("connection.pool_size"));
        hibernateProperties.put("javax.persistence.validation.mode", env.getProperty("javax.persistence.validation.mode"));

        return hibernateProperties;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setUrl(URL);
        dataSource.setDriverClassName(DRIVER);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan("com.aimprosoft.sandbox.domain");
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    /**
     * DATABASE CONFIG
     **/

    @Bean
    public DatabaseManager databaseManager() {
        return new DatabaseManager(URL, USER, PASSWORD);
    }


    /**
     * DAOs
     **/
    @Bean
    public EmployeeRepo hibernateEmployeeRepo() {
        return new HibernateEmployeeRepoImpl();
    }

    @Bean
    public DepartmentRepo hibernateDepartmentRepo() {
        return new HibernateDepartmentRepoImpl();
    }

    @Bean
    public DepartmentRepo springDepartmentRepo() {
        return new SpringOrmDepartmentRepoImpl();
    }

    @Bean
    public EmployeeRepo springEmployeeRepo() {
        return new SpringOrmEmployeeRepoImpl();
    }

    @Bean
    public DepartmentRepo jdbcDepartmentRepo() {
        return new DepartmentRepoImpl();
    }

    @Bean
    public EmployeeRepo jdbcEmployeeRepo() {
        return new EmployeeRepoImpl();
    }

    /**
     * SERVICES
     **/
    @Bean
    public DepartmentServiceImpl departmentService() {
        DepartmentServiceImpl service = new DepartmentServiceImpl();
        service.setRepo(springDepartmentRepo());
        return service;
    }

    @Bean
    public EmployeeServiceImpl employeeService() {
        EmployeeServiceImpl service = new EmployeeServiceImpl();
        service.setRepo(springEmployeeRepo());
        return service;
    }

    /**
     * ACTIONS
     **/
    @Bean
    public DepartmentsPage getDepartments() {
        return new DepartmentsPage();
    }

    @Bean
    public EmployeesPage getEmployees() {
        return new EmployeesPage();
    }

    @Bean
    public ErrorPage error() {
        return new ErrorPage();
    }

    @Bean
    public AddNewDepartment addDepartment() {
        return new AddNewDepartment();
    }

    @Bean
    public DeleteDepartment deleteDepartment() {
        return new DeleteDepartment();
    }

    @Bean
    public EditDepartment editDepartment() {
        return new EditDepartment();
    }

    @Bean
    public AddNewEmployee addEmployee() {
        return new AddNewEmployee();
    }

    @Bean
    public DeleteEmployee deleteEmployee() {
        return new DeleteEmployee();
    }

    @Bean
    public EditEmployee editEmployee() {
        return new EditEmployee();
    }

    /**
     * ACTION MAP
     **/
    @Bean
    public Map<String, Action> actionMap() {
        Map<String, Action> actionMap = new HashMap<>();

        actionMap.put("/departments", getDepartments());
        actionMap.put("/employees", getEmployees());
        actionMap.put("/error", error());

        actionMap.put("/departments/add", addDepartment());
        actionMap.put("/departments/delete", deleteDepartment());
        actionMap.put("/departments/edit", editDepartment());

        actionMap.put("/employees/add", addEmployee());
        actionMap.put("/employees/delete", deleteEmployee());
        actionMap.put("/employees/edit", editEmployee());

        return actionMap;
    }

    /**
     * ACTION MANAGER
     **/
    @Bean
    public ActionManager actionManager() {
        ActionManager actionManager = new ActionManager();
        actionManager.setActions(actionMap());
        return actionManager;
    }

    /**
     * VALIDATOR
     **/
    @Bean
    public OvalValidator validator() {
        return new OvalValidator();
    }
}
