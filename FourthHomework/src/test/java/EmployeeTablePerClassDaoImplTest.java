import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task8_table_per_class.dao.EmployeeTablePerClassDaoImpl;
import task8_table_per_class.model.EmployeeTablePerClass;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeTablePerClassDaoImplTest extends BaseDaoTest { //task8.3
    EmployeeTablePerClassDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new EmployeeTablePerClassDaoImpl(testSessionFactory);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("truncate table t_employee_table_per_class;");
        conn.close();
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void create() {
        //Given
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_employee_table_per_class;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        EmployeeTablePerClass employeeTablePerClass = EmployeeTablePerClass.builder()
                .name("Dima")
                .lastname("Vasin")
                .company("MTC")
                .salary(1234.56)
                .build();
        //When
        targetObject.create(employeeTablePerClass);
        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_employee_table_per_class;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table t_employee_table_per_class;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/EmployeeTablePerClassDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        EmployeeTablePerClass employeeTablePerClass = targetObject.findById(22222);
        assertNotNull(employeeTablePerClass);
        //Then
        assertEquals("Ivan", employeeTablePerClass.getName());
        assertEquals("Ivanov", employeeTablePerClass.getLastname());
        assertEquals("A1", employeeTablePerClass.getCompany());
        assertEquals(567.89, employeeTablePerClass.getSalary(), 0.0);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/EmployeeTablePerClassDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_employee_table_per_class;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
        //When
        targetObject.delete(targetObject.findById(22222));
        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_employee_table_per_class;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}

