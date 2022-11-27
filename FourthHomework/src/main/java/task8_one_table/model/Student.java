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
@DiscriminatorValue("S")
public class Student extends Person {
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "mark")
    private double mark;

    @Builder
    public Student(Integer id, String name, String surname, String faculty, double mark) {
        super(id, name, surname);
        this.faculty = faculty;
        this.mark = mark;
    }
}
