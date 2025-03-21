package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Course")
public abstract class Course {
    @Id
    @Column(name = "CourseID")
    protected int id;
    protected int credit;
    protected String title;

    @ManyToOne
    @JoinColumn(name = "department_id")
    protected Department department;
    @ManyToMany(mappedBy = "courses")
    Set<Instructor> instructors;
    public Course(int credit, String title) {
        this.credit = credit;
        this.title = title;
    }
}