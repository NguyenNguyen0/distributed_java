package models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
public class OnlineCourse extends Course {
    private String url;

    public OnlineCourse(int credit, String title, String url) {
        super(credit, title);
        this.url = url;
    }
}