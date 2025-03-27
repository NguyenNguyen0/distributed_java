package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "StudentGrade")
public class StudentGrade implements Serializable {
    @Id
    @Column(name = "EnrollmentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentID;
    @Column(name = "Grade")
    private double grade;

    @ManyToOne
    @JoinColumn(name = "StudentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "CourseId")
    private Course course;
}