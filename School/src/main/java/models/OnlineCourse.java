package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "OnlineCourse")
public class OnlineCourse extends Course implements Serializable {
    @Column(name = "Url")
    private String url;
}
