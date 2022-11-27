import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task8_one_table.dao.StudentDaoImpl;
import task8_one_table.model.Student;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StudentDaoImplTest extends BaseDaoTest { //task8.1
    StudentDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new StudentDaoImpl(testSessionFactory);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("truncate table t_person;");
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Student student = Student.builder()
                .name("Vasia")
                .surname("Kruglyi")
                .faculty("FPM")
                .mark(9.0)
                .build();
        //When
        targetObject.create(student);
        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_person;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table t_person;");
        conn.close();
    }
    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/StudentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        Student student = targetObject.findById(301);
        assertNotNull(student);
        //Then
        assertEquals("Pasha", student.getName());
        assertEquals("Simonov", student.getLastname());
        assertEquals("FacultyofPhysics", student.getFaculty());
        assertEquals(6.5,student.getMark(),0.0);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }
    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/StudentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(301));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_person;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }

}