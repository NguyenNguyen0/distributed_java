package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "Department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private int id;

    @Column(name = "Administrator")
    private int administrator;

    @Column(name = "Budget")
    private double budget;

    @Column(name = "Name")
    private String name;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    public Department(int administrator, double budget, String name, LocalDateTime startDate) {
        this.administrator = administrator;
        this.budget = budget;
        this.name = name;
        this.startDate = startDate;
    }
}