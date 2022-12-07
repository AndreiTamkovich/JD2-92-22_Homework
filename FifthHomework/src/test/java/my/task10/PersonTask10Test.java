package my.task10;

import lombok.SneakyThrows;
import my.task10.beans.Address;
import my.task10.beans.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class PersonTask10Test {
    @Test
    @SneakyThrows
    public void createAndDestroyPerson() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task10/beans.xml");
        Person p = context.getBean("person", Person.class);
        p.init();
        System.out.println(p.getAddress().toString());
        assertEquals("Alex",p.getName());
    }
}
