package my.dao;

import lombok.SneakyThrows;
import my.model.Person;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class PersonDaoImplTest extends BaseDaoTest {
    PersonDaoImpl targetObject;


    @Before
    public void setUp() throws Exception {
        targetObject = new PersonDaoImpl(testSessionFactory);
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
        Person person = Person.builder()
                .age(23)
                .name("Sasha")
                .surname("Ivanov")
                .build();
        //When
        targetObject.create(person);
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
    public void get() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/PersonDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        Person person = targetObject.get(11);
        assertNotNull(person);
        targetObject.delete(person);
        //Then
        assertEquals("Sasha", person.getName());
        assertEquals("Petrov", person.getSurname());
        assertEquals(22, (int) person.getAge());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/PersonDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        targetObject.delete(targetObject.get(11));
        //Then
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
    }
}