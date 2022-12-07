package my.task8.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressTask8 implements IAddressTask8 {
    private Long id;
    private String street;
    private Integer home;

    public AddressTask8() {
    }

    public AddressTask8(Long id, String street, Integer home) {
        this.id = id;
        this.street = street;
        this.home = home;
    }
}
