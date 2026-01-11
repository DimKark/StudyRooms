package com.dimandco.proj_studroom.core;

import java.time.LocalTime;

public class ReservationLogic {

    public static boolean canReserve(
            StudyRoom room,
            LocalTime from,
            LocalTime to
    ) {
        return !from.isBefore(room.getOpenFrom())
                && !to.isAfter(room.getOpenTo());
    }
}

