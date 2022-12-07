package my.task9.beans;

import lombok.Getter;
import lombok.Setter;
import my.task9.AddressAnnotated;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class PersonTask9 {
    Long id;
    String name;
    String surname;
    @Autowired
    @AddressAnnotated
    IAddressTask9 iAddressTask9;

    public static PersonTask9 getInstance() {
        return new PersonTask9();
    }

    public void init() {
        System.out.println("Person init(): " + id + " " + name + " " + surname);

    }

}
