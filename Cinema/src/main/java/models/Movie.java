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
@Table(name = "movies", indexes = {
        @Index(name = "idx_show_date_time", columnList = "director ASC")
})
public class Movie implements Serializable {
    @Id
    @Column(name = "movie_id")
    private String id;
    private String title;
    private String genre;
    @Column(name = "release_year")
    private int releaseYear;
    private String director;
    private int duration;

    @ToString.Exclude
    @OneToMany(mappedBy = "movie")
    private Set<Show> show;

    @ElementCollection
    @CollectionTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "actor"})
    )
    @Column(name = "actor", nullable = false)
    private Set<String> actors;
}
