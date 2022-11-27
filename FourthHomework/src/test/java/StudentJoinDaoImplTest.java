import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task8_join.dao.StudentJoinDaoImpl;
import task8_join.model.StudentJoin;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StudentJoinDaoImplTest extends BaseDaoTest { //task8.2
    StudentJoinDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new StudentJoinDaoImpl(testSessionFactory);
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_student_join;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        rs = conn.createStatement().executeQuery("select count(*) from t_person_join;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        StudentJoin studentJoin = StudentJoin.builder()
                .name("Sasha")
                .lastname("Ivanov")
                .faculty("FPM")
                .mark(9.0)
                .build();
        //When
        targetObject.create(studentJoin);
        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_person_join;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        rs = conn.createStatement().executeQuery("select count(*) from t_student_join;");
        rs.next();
        actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        conn.createStatement().executeUpdate("truncate table t_person_join;");
        conn.createStatement().executeUpdate("truncate table t_student_join;");
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        conn.close();
    }
    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/StudentJoinDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        StudentJoin studentJoin = targetObject.findById(1111);
        assertNotNull(studentJoin);
        //Then
        assertEquals("Sasha", studentJoin.getName());
        assertEquals("Petrova", studentJoin.getLastname());
        assertEquals("FacultyofPhysics", studentJoin.getFaculty());
        assertEquals(6.5,studentJoin.getMark(),0.0);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }
    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/StudentJoinDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person_join;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
        rs = conn.createStatement().executeQuery("select count(*) from t_student_join;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(1111));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_person_join;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        rs = conn.createStatement().executeQuery("select count(*) from t_student_join;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}
