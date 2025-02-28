package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int id;

    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

}
