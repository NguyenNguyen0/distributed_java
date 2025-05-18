package models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customers")
@AttributeOverride(name = "id", column = @Column(name = "customer_id"))
public class Customer extends Person implements java.io.Serializable {
    @EqualsAndHashCode.Exclude
    @Embedded
    @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code"))
    private Address address;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}
