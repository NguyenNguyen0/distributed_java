package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Address implements java.io.Serializable{
    private String street;
    private String city;
    private String state;

    @Column(name = "zip_code")
    private int zipCode;
}
