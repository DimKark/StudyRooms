package com.dimandco.proj_studroom.core.repository;

import com.dimandco.proj_studroom.core.model.ReservationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationHistoryRepository extends JpaRepository<ReservationLog, Long> {
    public Optional<ReservationLog> findById(final Long id);
}
