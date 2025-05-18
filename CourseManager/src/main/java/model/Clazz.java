package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clazzes")
public class Clazz {
    @Id
    @Column(name = "clazz_id")
    private String id;

    private String name;

    @Column(name = "no_of_students")
    private int noOfStudents;

    @OneToMany(mappedBy = "clazz")
    private Set<Student> students;

    @OneToOne
    private ClazzProfile profile;
}
