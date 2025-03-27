package model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Award {
    private int wins;
    private int nominations;
    private String text;
}
