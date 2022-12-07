package my.task9.beans;

import lombok.Getter;
import lombok.Setter;
import my.task9.AddressAnnotated;
@Getter
@Setter
@AddressAnnotated
public class AddressTask9 implements IAddressTask9 {
    Long id;
    String street;
    Long home;

    public AddressTask9() {
    }

    @Override
    public String toString() {
        return street + "," + home;
    }
}
