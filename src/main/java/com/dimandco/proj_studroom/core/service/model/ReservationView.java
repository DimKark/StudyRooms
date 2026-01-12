package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.model.Person;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationView(
        Long id,
        StudyRoom room,
        Person student,
        LocalDate date,
        LocalTime fromTime,
        LocalTime toTime,
        boolean active
) {}