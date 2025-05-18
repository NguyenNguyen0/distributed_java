package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "students")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Student {
    @Id
    @Column(name = "student_id")
    protected String id;

    protected String name;
    protected String email;
    protected LocalDate dob;

    @Enumerated(EnumType.STRING)
    protected Gender gender;

    @Embedded
    protected Address address;

    @ElementCollection
    @CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "phone", nullable = false)
    protected Set<String> phones;

    @ManyToOne
    @JoinColumn(name = "id")
    protected Clazz clazz;
}
