package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean gender;
    private String dateOfBirth;
    private String address;
}
