package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "people")
@Entity
public class Person implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "professional_title")
    private String professionalTitle;

    @ToString.Exclude
    @OneToMany(mappedBy = "person")
    private Set<Reviews> reviews;
}
