package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Instructor extends Person implements Serializable {
    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "PersonID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    private Set<Course> courses;
}
