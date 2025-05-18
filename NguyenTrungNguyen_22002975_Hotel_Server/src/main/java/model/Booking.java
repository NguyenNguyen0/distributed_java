package model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Booking implements Serializable {
    private String id;
    private Customer customer;
    private Room room;
    private LocalDate bookingDate;
}
