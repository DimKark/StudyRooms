package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.model.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationRequest(
        @NotNull @NotBlank StudyRoom room,
        @NotNull @NotBlank Person student,
        @NotNull @NotBlank LocalDate date,
        @NotNull @NotBlank LocalTime from,
        @NotNull @NotBlank LocalTime to
) {}
