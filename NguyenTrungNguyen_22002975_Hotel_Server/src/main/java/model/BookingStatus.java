package model;

import java.io.Serializable;

public enum BookingStatus implements Serializable {
    BOOKED,
    CHECKED_OUT,
    CANCELED,
    OCCUPIED;
}
