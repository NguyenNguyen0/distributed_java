package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable {
    private String id;
    private RoomType type;
    private int capacity;
    private double price;
}
