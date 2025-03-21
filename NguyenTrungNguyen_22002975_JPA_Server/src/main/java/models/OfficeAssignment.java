package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "OfficeAssignment")
public class OfficeAssignment {
    @Id
    @OneToOne
    @JoinColumn(name = "InstructorId")
    private Instructor instructor;
    private String location;
    private String timestamp;
}