package task8_join.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_employee_join")
@PrimaryKeyJoinColumn(name = "PERSON_ID")
public class EmployeeJoin extends PersonJoin {
    @Column(name = "company")
    private String company;
    @Column(name = "salary")
    private Double salary;

    @Builder
    public EmployeeJoin(Integer id, String name, String lastname, String company, Double salary) {
        super(id, name, lastname);
        this.company = company;
        this.salary = salary;
    }
}
