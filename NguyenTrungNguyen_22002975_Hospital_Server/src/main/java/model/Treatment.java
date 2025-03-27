package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Treatment implements Serializable {
    private static final long serialVersionUID = 1L;
    private Doctor doctor;
    private Patient patient;
    private LocalDate startDate;
    private LocalDate endDate;
    private String diagnosis;
}
