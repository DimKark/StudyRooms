package com.dimandco.proj_studroom.core.service.impl;

import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.model.ReservationLog;
import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.repository.PersonRepository;
import com.dimandco.proj_studroom.core.repository.ReservationHistoryRepository;
import com.dimandco.proj_studroom.core.repository.ReservationRepository;
import com.dimandco.proj_studroom.core.model.RoomReservation;
import com.dimandco.proj_studroom.core.repository.StudyRoomRepository;
import com.dimandco.proj_studroom.core.service.ReservationService;
import com.dimandco.proj_studroom.core.service.mapper.ReservationMapper;
import com.dimandco.proj_studroom.core.service.model.CreateReservationRequest;
import com.dimandco.proj_studroom.core.service.model.CreateReservationResult;
import com.dimandco.proj_studroom.core.service.model.ReservationView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final StudyRoomRepository studyRoomRepository;
    private final PersonRepository personRepository;
    private final ReservationHistoryRepository reservationHistoryRepository;

    public ReservationServiceImpl(ReservationMapper reservationMapper,
                                  ReservationRepository reservationRepository,
                                  StudyRoomRepository studyRoomRepository,
                                  PersonRepository personRepository,
                                  ReservationHistoryRepository reservationHistoryRepository) {
        if(reservationMapper == null) throw new NullPointerException("reservationMapper is null");
        if(reservationRepository == null) throw new NullPointerException("reservationMapper is null");
        if(studyRoomRepository == null) throw new NullPointerException("reservationMapper is null");
        if(personRepository == null) throw new NullPointerException("reservationMapper is null");
        if(reservationHistoryRepository == null)
            throw new NullPointerException("reservationHistoryRepository is null");
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
        this.studyRoomRepository = studyRoomRepository;
        this.personRepository = personRepository;
        this.reservationHistoryRepository = reservationHistoryRepository;
    }

    @Override
    public CreateReservationResult createReservation(CreateReservationRequest crr) {

        /* TODO Create replacement for this code
        for (RoomReservation r : reservations) {
            if (r.getActive() && r.overlaps(crr.from(), crr. to()))
                return CreateReservationResult.fail("There is already a reservation at this time");
        }
        */

        // --------------------------------------------------------------

        final Optional<StudyRoom> sr = studyRoomRepository.findByNameIgnoreCase(crr.roomName());
        if(sr.isEmpty()) return CreateReservationResult.fail("Room does not exist");

        RoomReservation reservation = new  RoomReservation();
        reservation.setId(null);
        reservation.setRoom(sr.get());
        reservation.setDate(crr.date());
        reservation.setFromTime(crr.from());
        reservation.setToTime(crr.to());
        reservation.setActive(true);

        if(sr.get().isWithinOpeningTime(crr.from(), crr.to()))
            return CreateReservationResult.fail("Desired time is outside opening hours");

        // --------------------------------------------------------------

        reservation = reservationRepository.save(reservation);

        final ReservationView reservationView =
                this.reservationMapper.convertReservationToReservationView(reservation);

        // Save reservation to history
        reservationHistoryRepository.save(new ReservationLog(reservation));

        return CreateReservationResult.success(reservationView);
    }

    public boolean deleteReservation(long id) {
        if(reservationRepository.findById(id).isPresent()) {
            reservationRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    ///** Print reservation history for debug purposes */
    /*
    public static String getReservationHistory() {
        StringBuilder s = new StringBuilder();
        for(ReservationLog rl : reservationHistory) {
            s.append(rl.toString() + "\n");
        }

        return s.toString();
    }
    */
}

