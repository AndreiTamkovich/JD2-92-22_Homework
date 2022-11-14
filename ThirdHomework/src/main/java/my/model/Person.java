package my.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Table(name = "t_person")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private long id;
    @Column(name = "age")
    private Integer age;
    @Column(name = "firstname")
    private String name;
    @Column(name = "lastname")
    private String surname;
}
