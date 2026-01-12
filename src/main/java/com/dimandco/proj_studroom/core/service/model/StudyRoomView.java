package com.dimandco.proj_studroom.core.service.model;

import java.time.LocalTime;

public record StudyRoomView (
        Long id,
        String name,
        int capacity,
        LocalTime openFrom,
        LocalTime openTo,
        boolean active
) {}
