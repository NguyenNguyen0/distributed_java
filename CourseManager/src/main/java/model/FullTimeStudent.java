package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "full_time_students")
public class FullTimeStudent extends Student {
    private String faculty;
    private String department;
}