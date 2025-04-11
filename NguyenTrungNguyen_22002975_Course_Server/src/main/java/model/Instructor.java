package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)


@Entity
@DiscriminatorValue("Instructor")
public class Instructor extends Person implements Serializable {

    private LocalDateTime hireDate;

    @ManyToMany
    @JoinTable(name = "CourseInstructor", joinColumns = @JoinColumn(name = "PersonID"), inverseJoinColumns = @JoinColumn(name = "CourseID"))
    private Set<Course> courses;
}
