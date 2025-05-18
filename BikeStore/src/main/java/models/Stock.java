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
@Table(name = "stocks")
public class Stock implements java.io.Serializable{
    @EmbeddedId
    private StockId id;

    private int quantity;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id")
    private Store store;

    @Embeddable
    static
    class StockId implements Serializable {
        private int productId;
        private int storeId;
    }
}
