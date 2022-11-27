package task8_join.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_person_join")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonJoin implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    @Column(name="id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String lastname;
}
