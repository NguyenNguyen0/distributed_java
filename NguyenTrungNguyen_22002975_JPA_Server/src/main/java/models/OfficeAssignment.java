package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "OfficeAssignment")
public class OfficeAssignment implements Serializable {
    @Id
    @Column(name = "InstructorID")
    private int instructorId;

    @Column(name = "Location")
    private String location;

    @Column(name = "Timestamp")
    private String timestamp;

    @OneToOne
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;
}