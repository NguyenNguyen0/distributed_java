package models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student {
    @SerializedName("student_id")
    private String id;
    private String name;
    private double gpa;

    @ToString.Exclude
    private Set<Course> courses;

    public Student(String studentId, String name, double gpa) {
        this.id = studentId;
        this.name = name;
        this.gpa = gpa;
    }
}
