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
@Table(name = "staffs")
@AttributeOverride(name = "id", column = @Column(name = "staff_id"))
public class Staff extends Person implements java.io.Serializable {
    private boolean active;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Staff manager;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "manager")
    private Set<Staff> staffs;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "staff")
    private Set<Order> orders;
}
