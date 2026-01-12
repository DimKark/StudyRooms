package com.dimandco.proj_studroom.core.service.mapper;

import com.dimandco.proj_studroom.core.model.RoomReservation;
import com.dimandco.proj_studroom.core.service.model.ReservationView;
import org.springframework.stereotype.Component;

/** Mapper to convert {@link RoomReservation} to {@link ReservationView} */
@Component
public class ReservationMapper {
    public ReservationView convertReservationToReservationView(final RoomReservation reservation) {
        if(reservation == null) return null;

        final ReservationView reservationView = new ReservationView(
                reservation.getId(),
                reservation.getRoom(),
                reservation.getStudent(),
                reservation.getDate(),
                reservation.getFromTime(),
                reservation.getToTime(),
                reservation.getActive()
        );

        return reservationView;
    }
}
