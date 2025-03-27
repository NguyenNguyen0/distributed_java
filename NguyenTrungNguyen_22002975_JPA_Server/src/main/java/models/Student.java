package models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("Student")
@Table(name = "Student")
public class Student extends Person implements Serializable {
    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;

    public Student(String firstName, String lastName, LocalDateTime enrollmentDate) {
        super(firstName, lastName);
        this.enrollmentDate = enrollmentDate;
    }
}