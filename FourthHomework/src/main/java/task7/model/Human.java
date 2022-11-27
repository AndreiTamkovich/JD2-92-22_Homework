package task7.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_human")
public class Human implements Serializable {
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
    private AboutHuman aboutHuman;
    private HumanAddress humanAddress;
}
