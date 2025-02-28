package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String username;
    @Column(columnDefinition = "varchar(45)", nullable = false)
    private String password;
    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String email;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups;
}
