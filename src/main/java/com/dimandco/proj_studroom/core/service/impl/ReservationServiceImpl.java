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

        // --------------------------------------------------------------

        final Optional<StudyRoom> srOpt = studyRoomRepository.findByNameIgnoreCase(crr.roomName());
        if(srOpt.isEmpty()) return CreateReservationResult.fail("Room does not exist");

        RoomReservation reservation = new  RoomReservation();
        reservation.setId(null);
        reservation.setRoom(srOpt.get());
        reservation.setStudent(crr.student());
        reservation.setDate(crr.date());
        reservation.setFromTime(crr.from());
        reservation.setToTime(crr.to());
        reservation.setActive(true);

        // Check if reservation time is withing room opening hours
        StudyRoom sr = reservation.getRoom();
        if(!reservation.getRoom().isWithinOpeningTime(reservation.getFromTime(), reservation.getToTime()))
            return CreateReservationResult.fail("Desired time is outside opening hours (" +
                    sr.getOpenFrom() + " - " + sr.getOpenTo() + ")");

        // Check if desired room is free on specific date and time
        List<RoomReservation> rl = reservationRepository.findAll();
        for(RoomReservation rr : rl) {
            if(reservation.overlaps(rr)) {
                return CreateReservationResult.fail(
                        "There is already a reservation on this room from " +
                        rr.getFromTime().toString() + " to " + rr.getToTime().toString()
                );
            }
        }
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

