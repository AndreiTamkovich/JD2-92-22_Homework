package my.task10.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Person {
    @Value("1")
    Long id;
    @Value("Alex")
    String name;
    @Value("Ivanov")
    String surname;
    @Value("#{address}")
    Address address;

    public void init() {
        System.out.println("Person init(): " + id + " " + name + " " + surname);
    }
}
