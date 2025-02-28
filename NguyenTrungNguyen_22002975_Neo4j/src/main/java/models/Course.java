package models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Course {
    @SerializedName("course_id")
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
