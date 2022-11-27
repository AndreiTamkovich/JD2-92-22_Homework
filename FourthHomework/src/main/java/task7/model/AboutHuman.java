package task7.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class AboutHuman {
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private long age;
}
