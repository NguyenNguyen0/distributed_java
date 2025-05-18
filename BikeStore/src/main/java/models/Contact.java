package models;

import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Contact implements java.io.Serializable {
    private String phone;
    private String email;
}
