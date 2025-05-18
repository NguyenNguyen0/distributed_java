package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clazz_profiles")
public class ClazzProfile {
    @Id
    @Column(name = "profile_id")
    private String id;

    @OneToOne(mappedBy = "profile")
    @JoinColumn(name = "clazz_id", nullable = false, unique = true)
    private Clazz clazz;

    @Column(name = "create_date")
    private LocalDate createDate;

    private String description;
}
