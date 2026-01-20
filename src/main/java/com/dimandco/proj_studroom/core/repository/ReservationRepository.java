package com.dimandco.proj_studroom.core.repository;

import com.dimandco.proj_studroom.core.model.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<RoomReservation, Long> {
    Optional<RoomReservation> findById(final Long id);
    List<RoomReservation> findAll();
    List<RoomReservation> findAllByRoom_Id(final Long id);
}
