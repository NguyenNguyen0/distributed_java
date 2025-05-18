package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "Department")
public class Department implements Serializable {
    @Id
    @Column(name = "DepartmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Administrator")
    private int administrator;

    @Column(name = "Budget")
    private double budget;

    @Column(name = "Name")
    private String name;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "department")
    private Set<Course> courses;
}
