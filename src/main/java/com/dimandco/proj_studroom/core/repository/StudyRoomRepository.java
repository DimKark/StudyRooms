package com.dimandco.proj_studroom.core.repository;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Long> {
    Optional<StudyRoom> findById(final Long id);
    Optional<StudyRoom> findByNameIgnoreCase(final String name);
}