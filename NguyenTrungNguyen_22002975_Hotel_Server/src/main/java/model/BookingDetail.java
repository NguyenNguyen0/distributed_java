package model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BookingDetail implements Serializable {
    private String bookingId;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BookingStatus status;
}
