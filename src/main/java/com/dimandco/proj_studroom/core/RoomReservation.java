package com.dimandco.proj_studroom.core;

import com.dimandco.proj_studroom.core.model.Person;

import java.time.LocalDate;
import java.time.LocalTime;

public class RoomReservation {

    private Long id;
    private StudyRoom room;
    private Person student;
    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private boolean active;

    public boolean overlaps(LocalTime from, LocalTime to) {
        return from.isBefore(this.toTime) && to.isAfter(this.fromTime);
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public boolean isActive() {
        return active;
    }

    public void cancelReservation() {
        this.active= false;
    }
    public Person getStudent() {
        return student;
    }
}


