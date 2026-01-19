package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.web.ui.ReservationController;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationRequest(
         String roomName,
         Person student,
         LocalDate date,
         LocalTime from,
         LocalTime to
) {
    /** Returns an empty request with the given student, specifically made for
     * {@link ReservationController#handleReservationSubmission}
     */
    public static CreateReservationRequest empty() {
        return new CreateReservationRequest(null, null, null, null, null);
    }

    /** Returns the same {@link CreateReservationRequest} with added student */
    public static CreateReservationRequest withStudent(CreateReservationRequest crr, Person student) {
        return new CreateReservationRequest(
                crr.roomName(),
                student,
                crr.date(),
                crr.from(),
                crr.to()
        );
    }
}
