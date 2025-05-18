package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private String id;
    private String name;
    private int yearOfBirth;
    private String phone;
    private String address;
}
