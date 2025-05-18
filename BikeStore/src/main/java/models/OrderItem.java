package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "order_items")
public class OrderItem implements java.io.Serializable{
    @EmbeddedId
    private OrderItemId id;

    private int quantity;

    @Column(name = "list_price")
    private double listPrice;
    private double discount;

    @ToString.Exclude
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ToString.Exclude
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Embeddable
    static
    class OrderItemId implements Serializable {
        private int productId;
        private int orderId;
    }
}
