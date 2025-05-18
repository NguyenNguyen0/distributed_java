package models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "orders")
public class Order implements java.io.Serializable{
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_status")
    private boolean orderStatus;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "required_date")
    private LocalDate requiredDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;
}
