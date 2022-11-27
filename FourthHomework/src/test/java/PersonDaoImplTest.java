import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task8_one_table.dao.PersonDaoImpl;
import task8_one_table.model.Person;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonDaoImplTest extends BaseDaoTest { //task8.1
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
        Person person = new Person();
        person.setName("Sasha");
        person.setLastname("Ivanov");
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
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/PersonDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        Person person = targetObject.findById(11);
        assertNotNull(person);
        //Then
        assertEquals("Sasha", person.getName());
        assertEquals("Petrova", person.getLastname());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/PersonDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        targetObject.delete(targetObject.findById(11));
        //Then
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
    }
}