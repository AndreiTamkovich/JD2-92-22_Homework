import lombok.SneakyThrows;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.BeforeClass;
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
import util.MysqlJdbcDataSource;

public class BaseDaoTest {
    // JDBC data source
    static MysqlJdbcDataSource testMysqlJdbcDataSource;
    // DBUnit connection
    static IDatabaseConnection iDatabaseConnection;
    //Hibernate session factory
    static SessionFactory testSessionFactory;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testMysqlJdbcDataSource = new MysqlJdbcDataSource("cfg/homework4schema_test.properties");
        iDatabaseConnection = new MySqlConnection(testMysqlJdbcDataSource.getConnection(), "homework4schema_test");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("cfg/hibernate-test.cfg.xml")
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
                .addAnnotatedClass(Human.class)
                .addAnnotatedClass(AboutHuman.class)
                .addAnnotatedClass(HumanAddress.class)
                .getMetadataBuilder()
                .build();
        testSessionFactory = metadata.getSessionFactoryBuilder()
                .build();
    }
}
