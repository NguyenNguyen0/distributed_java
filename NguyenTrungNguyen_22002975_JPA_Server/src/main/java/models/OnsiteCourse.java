package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@Table(name = "onsite_course")
public class OnsiteCourse extends Course {
    private String days;
    private String location;
    private LocalDateTime time;

    public OnsiteCourse(int credit, String title, String days, String location, LocalDateTime time) {
        super(credit, title);
        this.days = days;
        this.location = location;
        this.time = time;
    }
}