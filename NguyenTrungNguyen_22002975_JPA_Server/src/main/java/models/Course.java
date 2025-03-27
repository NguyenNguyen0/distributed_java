package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Course")
public abstract class Course implements Serializable {
    @Id
    @Column(name = "CourseID")
    protected int id;

    @Column(name = "Credits")
    protected int credit;

    @Column(name = "Title")
    protected String title;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    protected Department department;

    @ManyToMany
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "PersonID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    Set<Instructor> instructors;

    public Course(int credit, String title) {
        this.credit = credit;
        this.title = title;
    }
}