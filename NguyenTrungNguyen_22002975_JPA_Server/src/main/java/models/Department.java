package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int administrator;
    private double budget;
    private String name;
    private LocalDateTime startDate;

    public Department(int administrator, double budget, String name, LocalDateTime startDate) {
        this.administrator = administrator;
        this.budget = budget;
        this.name = name;
        this.startDate = startDate;
    }
}