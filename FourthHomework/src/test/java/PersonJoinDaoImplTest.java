import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task8_join.dao.PersonJoinDaoImpl;
import task8_join.model.PersonJoin;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonJoinDaoImplTest extends BaseDaoTest { //task8.2
    PersonJoinDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new PersonJoinDaoImpl(testSessionFactory);
        /*Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("truncate table t_person_join;");
        conn.close();*/
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person_join;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        PersonJoin personJoin = new PersonJoin();
        personJoin.setName("Sasha");
        personJoin.setLastname("Ivanov");
        //When
        targetObject.create(personJoin);
        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_person_join;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        conn.createStatement().executeUpdate("truncate table t_person_join;");
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/PersonJoinDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        PersonJoin personJoin = targetObject.findById(1111);
        assertNotNull(personJoin);
        //Then
        assertEquals("Sasha", personJoin.getName());
        assertEquals("Petrova", personJoin.getLastname());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }


    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task8_xml_resources/PersonJoinDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        targetObject.delete(targetObject.findById(1111));
        //Then
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_person_join;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
    }
}
