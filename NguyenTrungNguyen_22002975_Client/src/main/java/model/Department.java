package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@ToString
@AllArgsConstructor
@Setter
@Getter
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String location;
    private Set<Doctor> doctors;
}
