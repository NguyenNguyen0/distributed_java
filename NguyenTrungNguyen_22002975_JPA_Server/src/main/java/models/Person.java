package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator")
@Table(name = "Person")
public abstract class Person implements Serializable {
    @Id
    @Column(name = "PersonID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "FirstName")
    protected String firstName;

    @Column(name = "LastName")
    protected String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}