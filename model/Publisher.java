package model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="publishers")
@Entity
public class Publisher implements java.io.Serializable {
    @Id
    @Column(name="publisher_id")
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;
}
