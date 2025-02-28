package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Course {
    private String id;
    private String name;
    private int hours;

    private Department department;

    public Course(String id, String name, int hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }
}
