package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @EqualsAndHashCode.Exclude
    private String username;
    @EqualsAndHashCode.Exclude
    private String password;
    private String email;

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Group> groups;
}
