package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "reviews")
@Entity
@IdClass(Reviews.ReviewID.class)
public class Reviews implements java.io.Serializable {
    private int rating;
    private String comment;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;

    @AllArgsConstructor
    @NoArgsConstructor
    public class ReviewID implements java.io.Serializable {
        private Person person;
        private Book book;
    }
}
