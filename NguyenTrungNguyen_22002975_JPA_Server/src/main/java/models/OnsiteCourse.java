package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@Table(name = "OnsiteCourse")
public class OnsiteCourse extends Course implements Serializable {
    @Column(name = "Days")
    private String days;

    @Column(name = "Location")
    private String location;

    @Column(name = "Time")
    private LocalDateTime time;

    public OnsiteCourse(int credit, String title, String days, String location, LocalDateTime time) {
        super(credit, title);
        this.days = days;
        this.location = location;
        this.time = time;
    }
}