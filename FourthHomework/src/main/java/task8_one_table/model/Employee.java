package task8_one_table.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("E")
public class Employee extends Person {
    @Column(name = "company")
    private String company;
    @Column(name = "salary")
    private Double salary;

    @Builder
    public Employee(Integer id, String name, String lastname, String company, Double salary) {
        super(id, name, lastname);
        this.company = company;
        this.salary = salary;
    }
}
