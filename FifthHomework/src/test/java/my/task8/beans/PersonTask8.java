package my.task8.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class PersonTask8 implements InitializingBean, DisposableBean {
    Long id;
    String name;
    String surname;
    @Autowired
    AddressTask8 addressTask8;

    public static PersonTask8 getInstance() {
        return new PersonTask8();
    }

    public void init() {
        System.out.println("Person init() with address: " + id + " " + name + " " + surname
                + " " + addressTask8.getStreet() + "," + addressTask8.getHome());
    }

    @Override
    public void destroy() throws Exception {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}