package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "OnsiteCourse")
public class OnsiteCourse extends Course implements Serializable {
    @Column(name = "Days")
    private String days;

    @Column(name = "Location")
    private String location;

    @Column(name = "Time")
    private LocalTime time;
}
