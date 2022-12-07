package my.task2;

import lombok.SneakyThrows;
import my.task2.beans.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    @Test
    @SneakyThrows
    public void createAndDestroyPerson() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task2/beans.xml");
        Person p1 = context.getBean("person", Person.class);
        assertEquals("Alex", p1.getName());
        p1.destroy();
    }
}
