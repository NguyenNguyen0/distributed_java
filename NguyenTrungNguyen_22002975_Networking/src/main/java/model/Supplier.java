package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private int supplierID;
    private String companyName;
    private ContactInfo contactInfo;
    private Address address;
    private String homePage;
}