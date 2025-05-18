package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Book implements java.io.Serializable {
    @Id
    private String ISBN;
    private String name;
    @Column(name = "publish_year")
    private int publishYear;
    @Column(name = "num_of_pages")
    private int numberOfPages;
    private double price;

    @ElementCollection
    @CollectionTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "ISBN"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"ISBN", "author"}))
    @Column(name = "author")
    private Set<String> authors;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ToString.Exclude
    @OneToMany(mappedBy = "book")
    private Set<Reviews> reviews;
}
