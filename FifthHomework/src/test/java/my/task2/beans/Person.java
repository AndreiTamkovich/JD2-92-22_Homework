package my.task2.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Getter
@Setter
public class Person implements InitializingBean, DisposableBean {
    long id;
    String name;
    String surname;

    public static Person getInstance() {
        return new Person();
    }

    public void init() {
        System.out.println("Person init(): " + id + " " + name + " " + surname);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Person destroy(): " + id + " " + name + " " + surname);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person afterPropertiesSet(): " + id + " " + name + " " + surname);
    }
}
