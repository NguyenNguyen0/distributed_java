package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id")
    private String id;
    private String name;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer")
    private Set<Ticket> tickets;
}
