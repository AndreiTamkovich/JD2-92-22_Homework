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
@Table(name = "t_student_join")
@PrimaryKeyJoinColumn(name = "PERSON_ID")
public class StudentJoin extends PersonJoin {
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "mark")
    private double mark;

    @Builder
    public StudentJoin(Integer id, String name, String lastname, String faculty, double mark) {
        super(id, name, lastname);
        this.faculty = faculty;
        this.mark = mark;
    }
}
