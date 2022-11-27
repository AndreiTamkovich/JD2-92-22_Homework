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
@Table(name = "t_student_table_per_class")
public class StudentTablePerClass extends PersonTablePerClass {
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "mark")
    private double mark;

    @Builder
    public StudentTablePerClass(Integer id, String name, String lastname, String faculty, double mark) {
        super(id, name, lastname);
        this.faculty = faculty;
        this.mark = mark;
    }
}
