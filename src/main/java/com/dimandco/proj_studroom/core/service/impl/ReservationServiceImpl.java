package com.dimandco.proj_studroom.core.service.impl;

import com.dimandco.proj_studroom.core.repository.ReservationRepository;
import com.dimandco.proj_studroom.core.model.RoomReservation;
import com.dimandco.proj_studroom.core.service.ReservationService;
import com.dimandco.proj_studroom.core.service.mapper.ReservationMapper;
import com.dimandco.proj_studroom.core.service.model.CreateReservationRequest;
import com.dimandco.proj_studroom.core.service.model.CreateReservationResult;
import com.dimandco.proj_studroom.core.service.model.ReservationView;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationMapper reservationMapper, ReservationRepository reservationRepository) {
        if(reservationMapper == null) throw new NullPointerException("reservationMapper is null");
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public CreateReservationResult createReservation(CreateReservationRequest crr) {
        if(crr.room().isWithinOpeningTime(crr.from(), crr.to()))
            return CreateReservationResult.fail("Desired time is outside opening hours");

        /* TODO Create replacement for this code
        for (RoomReservation r : reservations) {
            if (r.getActive() && r.overlaps(crr.from(), crr. to()))
                return CreateReservationResult.fail("There is already a reservation at this time");
        }
        */

        // --------------------------------------------------------------

        RoomReservation reservation = new  RoomReservation();
        reservation.setId(null);
        reservation.setRoom(crr.room());
        reservation.setStudent(crr.student());
        reservation.setDate(crr.date());
        reservation.setFromTime(crr.from());
        reservation.setToTime(crr.to());
        reservation.setActive(true);

        // --------------------------------------------------------------

        // TODO UNCOMMENT
        reservation = reservationRepository.save(reservation);

        final ReservationView reservationView =
                this.reservationMapper.convertReservationToReservationView(reservation);

        return CreateReservationResult.success(reservationView);
    }

    public boolean deleteReservation(long id) {
        if(reservationRepository.findById(id).isPresent()) {
            reservationRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    // TODO RESERVATION HISTORY
}

