package models;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student_grade")
public class StudentGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentID;
    private double grade;
    @ManyToOne
    @JoinColumn(name = "student_person_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course_course_id")
    private Course course;
}