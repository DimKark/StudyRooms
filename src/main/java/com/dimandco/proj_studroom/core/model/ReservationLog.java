package com.dimandco.proj_studroom.core.model;

import java.time.Instant;

/**
 * Class to record {@link RoomReservation} history
 */
public class ReservationLog {
    private final RoomReservation reservation;
    private final Instant reservationDate;

    public ReservationLog(RoomReservation r) {
        this.reservation = r;
        this.reservationDate = Instant.now();
    }

    @Override
    public String toString() {
        String s =
            "RESERVATION:\n" + this.reservation.toString() + "\n\n" +
            "RESERVATION MADE ON: " + reservationDate.toString();

        return s;
    }
}
