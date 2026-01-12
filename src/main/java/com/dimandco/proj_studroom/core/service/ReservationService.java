package com.dimandco.proj_studroom.core.service;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.service.model.CreateReservationRequest;
import com.dimandco.proj_studroom.core.service.model.CreateReservationResult;

/** Service for managing {@link StudyRoom} reservations */
public interface ReservationService {
    public CreateReservationResult createReservation(CreateReservationRequest createReservationRequest);
}
