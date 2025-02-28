package iuh.fit.entities;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {
    private String type;
    private String number;
}