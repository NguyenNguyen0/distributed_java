package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "shows", indexes = {
        @Index(name = "idx_show_date_time", columnList = "show_date_time ASC")
})
public class Show implements Serializable {
    @Id
    @Column(name = "show_id")
    private String id;

    @Column(name = "show_date_time")
    private LocalDateTime showDateTime;

    @Column(name = "hall_name")
    private String hallName;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ToString.Exclude
    @OneToMany(mappedBy = "show")
    private Set<Ticket> tickets;
}
