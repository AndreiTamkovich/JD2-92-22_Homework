package my.task9;

import lombok.SneakyThrows;
import my.task9.beans.PersonTask9;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class PersonTask9Test {
    @Test
    @SneakyThrows
    public void createAndDestroyPerson() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task9/beans.xml");
        PersonTask9 p1 = context.getBean("persontask9", PersonTask9.class);
        System.out.println(p1.getIAddressTask9().toString());
        assertEquals("Alex", p1.getName());
    }
}
