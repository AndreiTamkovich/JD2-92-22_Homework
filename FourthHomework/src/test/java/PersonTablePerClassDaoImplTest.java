import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task8_table_per_class.dao.PersonTablePerClassDaoImpl;
import task8_table_per_class.model.PersonTablePerClass;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonTablePerClassDaoImplTest extends BaseDaoTest{
    PersonTablePerClassDaoImpl targetObject;
    @Before
    public void setUp() throws Exception {
        targetObject = new PersonTablePerClassDaoImpl(testSessionFactory);
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person_table_per_class;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        PersonTablePerClass personTablePerClass = new PersonTablePerClass();
        personTablePerClass.setName("Sasha");
        personTablePerClass.setLastname("Ivanov");
        //When
        targetObject.create(personTablePerClass);
        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_person_table_per_class;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table t_person_table_per_class;");
        conn.close();
    }
    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/PersonTablePerClassDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        PersonTablePerClass personTablePerClass = targetObject.findById(11111);
        assertNotNull(personTablePerClass);
        //Then
        assertEquals("Sasha", personTablePerClass.getName());
        assertEquals("Petrova", personTablePerClass.getLastname());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }
    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/PersonTablePerClassDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        targetObject.delete(targetObject.findById(11111));
        //Then
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person_table_per_class;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
    }
}
