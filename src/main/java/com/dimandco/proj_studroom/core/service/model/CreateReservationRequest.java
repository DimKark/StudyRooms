package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.model.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationRequest(
         String roomName,
         String studentHuaId,
         LocalDate date,
         LocalTime from,
         LocalTime to
) {
    public static CreateReservationRequest empty() {
        return new CreateReservationRequest(null, null, null, null, null);
    }
}
