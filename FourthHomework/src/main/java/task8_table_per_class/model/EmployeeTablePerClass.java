package task8_table_per_class.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_employee_table_per_class")
public class EmployeeTablePerClass extends PersonTablePerClass {
    @Column(name = "company")
    private String company;
    @Column(name = "salary")
    private Double salary;

    @Builder
    public EmployeeTablePerClass(Integer id, String name, String lastname, String company, Double salary) {
        super(id, name, lastname);
        this.company = company;
        this.salary = salary;
    }
}
