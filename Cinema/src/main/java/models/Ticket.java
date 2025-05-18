package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
    @Id
    @Column(name = "ticket_number")
    private String ticketNumber;
    private String seat;
    private double price;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
