package com.dimandco.proj_studroom.core.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * Class to record {@link RoomReservation} history
 */
@Entity
@Table(name = "reservation_history")
public class ReservationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private final Long id;

    @OneToOne
    @JoinColumn
    private final RoomReservation reservation;

    @CreationTimestamp
    private final Instant reservationDate;

    public ReservationLog() {
        this.id = null;
        this.reservation = null;
        this.reservationDate = null;
    }

    public ReservationLog(RoomReservation r) {
        this.id = null;
        this.reservation = r;
        this.reservationDate = Instant.now();
    }

    public Long getId() { return id; }

    public RoomReservation getReservation() { return reservation; }

    public Instant getReservationDate() { return reservationDate; }


    @Override
    public String toString() {
        String s =
            "RESERVATION:\n" + this.reservation.toString() + "\n\n" +
            "RESERVATION MADE ON: " + reservationDate.toString();

        return s;
    }
}
