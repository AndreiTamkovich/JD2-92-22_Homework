package my.task10.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("address")
@Getter
@Setter
public class Address implements IAddress {
    @Value("1")
    Long id;
    @Value("Gikalo")
    String street;
    @Value("3")
    Long home;

    public Address() {
    }

    public Address(Long id, String street, Long home) {
        this.id = id;
        this.street = street;
        this.home = home;
    }

    @Override
    public String toString() {
        return street + "," + home;
    }
}
