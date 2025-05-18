package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Student extends Person implements Serializable {
    @Column(name = "EnrollmentDate")
    private String enrollmentDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student")
    private Set<StudentGrade> studentGrades;
}
