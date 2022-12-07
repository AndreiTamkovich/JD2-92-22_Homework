package my.task8;

import lombok.SneakyThrows;
import my.task8.beans.PersonTask8;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class PersonTask8Test {
    @Test
    @SneakyThrows
    public void createAndDestroyPerson() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task8/beans.xml");
        PersonTask8 p1 = context.getBean("persontask8", PersonTask8.class);
        assertEquals("Alex", p1.getName());
        p1.destroy();
    }
}