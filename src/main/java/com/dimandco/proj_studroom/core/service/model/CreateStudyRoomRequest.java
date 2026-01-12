package com.dimandco.proj_studroom.core.service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CreateStudyRoomRequest(
        @NotNull @NotBlank String name,
        @NotNull @NotBlank int capacity,
        @NotNull @NotBlank LocalTime openFrom,
        @NotNull @NotBlank LocalTime openTo,
        @NotNull @NotBlank boolean active
) {}