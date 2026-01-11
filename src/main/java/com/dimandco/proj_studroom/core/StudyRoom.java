package com.dimandco.proj_studroom.core;

import lombok.Getter;

import java.time.LocalTime;

public class StudyRoom {

    private Long id;
    private String name;
    private int capacity;
    private LocalTime openFrom;
    private LocalTime openTo;
    private boolean active = true;

    public LocalTime getOpenFrom() {
        return openFrom;
    }

    public LocalTime getOpenTo() {
        return openTo;
    }
}

