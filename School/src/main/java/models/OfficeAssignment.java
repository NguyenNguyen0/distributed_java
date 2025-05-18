package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString()
@EqualsAndHashCode
@Entity
@Table(name = "OfficeAssignment")
public class OfficeAssignment implements Serializable {
    @Column(name = "Location")
    private String location;

    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Id
    @OneToOne
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;
}
