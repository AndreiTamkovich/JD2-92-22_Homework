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
public class HumanAddress {
    @Column(name = "country")
    private String county;
    @Column(name = "city")
    private String city;
}
