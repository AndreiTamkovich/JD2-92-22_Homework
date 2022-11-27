import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task7.dao.HumanDaoImpl;
import task7.model.AboutHuman;
import task7.model.Human;
import task7.model.HumanAddress;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HumanDaoImplTest extends BaseDaoTest {
    HumanDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new HumanDaoImpl(testSessionFactory);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("truncate table t_human;");
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
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_human;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Human human = Human.builder()
                .name("Alexei")
                .aboutHuman(AboutHuman.builder().age(30).gender("male").build())
                .humanAddress(HumanAddress.builder().county("Belarus").city("Minsk").build())
                .build();
        //When
        targetObject.create(human);
        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_human;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table t_human;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task7_xml_resources/HumanDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        Human human = targetObject.findById(100000);
        assertNotNull(human);
        //Then
        assertEquals("Alex", human.getName());
        assertEquals("male", human.getAboutHuman().getGender());
        assertEquals(25, human.getAboutHuman().getAge());
        assertEquals("Belarus", human.getHumanAddress().getCounty());
        assertEquals("Borisov", human.getHumanAddress().getCity());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/task7_xml_resources/HumanDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        //When
        targetObject.delete(targetObject.findById(100000));
        //Then
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_human;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
    }
}
