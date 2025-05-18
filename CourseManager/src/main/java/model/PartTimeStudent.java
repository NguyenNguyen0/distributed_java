package model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "part_time_students")
public class PartTimeStudent extends Student {
    private String supervisor;
}