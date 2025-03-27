package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
public class OnlineCourse extends Course implements Serializable {
    @Column(name = "URL")
    private String url;

    public OnlineCourse(int credit, String title, String url) {
        super(credit, title);
        this.url = url;
    }
}