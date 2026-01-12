package com.dimandco.proj_studroom.core.service.model;

public record CreateReservationResult(
        boolean created,
        String reason,
        ReservationView reservationView
) {
    public  static CreateReservationResult success(final ReservationView reservationView) {
        if(reservationView == null) throw new NullPointerException("reservationView is null");
        return new CreateReservationResult(true, null, reservationView);
    }

    public static CreateReservationResult fail(final String reason) {
        if(reason == null) throw new NullPointerException("reason is null");
        if(reason.isBlank()) throw new IllegalArgumentException("reason is empty");

        return new CreateReservationResult(false, reason, null);
    }
}
