package com.dimandco.proj_studroom.core;

import com.dimandco.proj_studroom.core.model.Person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final List<RoomReservation> reservations = new ArrayList<>();

    public boolean createReservation(
            StudyRoom room,
            Person student,
            LocalDate date,
            LocalTime from,
            LocalTime to
    ) {

        if (from.isBefore(room.getOpenFrom()) ||
                to.isAfter(room.getOpenTo())) {
            System.out.println("Wrong date");
            return false;
        }

        for (RoomReservation r : reservations) {
            if (r.isActive()
                    && r.overlaps(from, to)) {
                System.out.println("There is already a reservation at this time");
                return false;
            }
        }

        RoomReservation reservation = new RoomReservation();
        reservations.add(reservation);
        System.out.println("Reservation confirmed");
        return true;
    }

    public boolean  deleteReservation(
            long reservationId
    ) {
        for (RoomReservation r : reservations) {
            if (r.getStudent().equals(reservationId) && r.isActive()) {
                r.cancelReservation();
                System.out.println("Reservation canceled");
                return true;
            }
        }
        System.out.println("No active reservation was found");
        return false;
    }
}

