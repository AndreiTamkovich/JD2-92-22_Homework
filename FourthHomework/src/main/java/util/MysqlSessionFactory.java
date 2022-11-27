package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import task6.model.ProductIncrement;
import task6.model.ProductSequence;
import task7.model.AboutHuman;
import task7.model.Human;
import task7.model.HumanAddress;
import task8_join.model.EmployeeJoin;
import task8_join.model.PersonJoin;
import task8_join.model.StudentJoin;
import task8_one_table.model.Employee;
import task8_one_table.model.Person;
import task8_one_table.model.Student;
import task8_table_per_class.model.EmployeeTablePerClass;
import task8_table_per_class.model.PersonTablePerClass;
import task8_table_per_class.model.StudentTablePerClass;

import java.util.HashMap;
import java.util.Map;

public class MysqlSessionFactory {
    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        return getInstance(DataConfig.JDBC_PROPERTIES_FILE_NAME,
                DataConfig.HIBERNATE_PROPERTIES_FILE_NAME);
    }

    public static SessionFactory getInstance(String jdbcPropertiesFileName,
                                             String hibernatePropertiesFileName) {
        if (sessionFactory != null) {
            return sessionFactory;
        }

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .applySettings(buildSettings(jdbcPropertiesFileName,
                        hibernatePropertiesFileName))
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(PersonJoin.class)
                .addAnnotatedClass(EmployeeJoin.class)
                .addAnnotatedClass(StudentJoin.class)
                .addAnnotatedClass(PersonTablePerClass.class)
                .addAnnotatedClass(EmployeeTablePerClass.class)
                .addAnnotatedClass(StudentTablePerClass.class)
                .addAnnotatedClass(ProductIncrement.class)
                .addAnnotatedClass(ProductSequence.class)
                .addAnnotatedClass(Human.class)
                .addAnnotatedClass(AboutHuman.class)
                .addAnnotatedClass(HumanAddress.class)
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }

    private static Map<String, String> buildSettings(String jdbcPropertiesFileName,
                                                     String hibernatePropertiesFileName) {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("driver"));
        settings.put("hibernate.connection.url",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("url"));
        settings.put("hibernate.connection.username",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("username"));
        settings.put("hibernate.connection.password",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("password"));

        settings.put("dialect",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("dialect"));
        settings.put("hibernate.current_session_context_class",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.current_session_context_class"));
        settings.put("hibernate.show_sql",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.show_sql"));
        settings.put("hibernate.format_sql",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.format_sql"));
        return settings;
    }
}

