package models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@DiscriminatorValue("Instructor")
public class Instructor extends Person implements Serializable {
    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @ManyToMany(mappedBy = "instructors")
    private Set<Course> courses;

    public Instructor(String firstName, String lastName, LocalDateTime hireDate) {
        super(firstName, lastName);
        this.hireDate = hireDate;
    }
}