package model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String speciality;

    public Doctor(String Id, String name, String phone, String speciality) {
        super(Id, name, phone);
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "ID='" + getId() + '\'' +
                ", Name='" + getName() + '\'' +
                ", Phone='" + getPhone() + '\'' +
                ", Speciality='" + speciality + '\'' +
                '}';
    }
}
