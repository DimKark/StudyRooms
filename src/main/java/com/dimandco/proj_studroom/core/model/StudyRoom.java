package com.dimandco.proj_studroom.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

/** Study Room Entity */
@Entity
@Table//(name = "studyroom")
public final class StudyRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private int capacity;

    @NotNull
    @Column
    private LocalTime openFrom;

    @NotNull
    @Column
    private LocalTime openTo;

    @NotNull
    @Column
    private boolean active = true;

    public StudyRoom() {
        this.id = null;
        this.name = "a";
        this.capacity = 5;
        this.openFrom = LocalTime.now();
        this.openTo = LocalTime.now();
        this.active = true;
    }

    // -------------------- GETTERS AND SETTERS --------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public LocalTime getOpenFrom() { return openFrom; }
    public void setOpenFrom(LocalTime openFrom) { this.openFrom = openFrom; }

    public LocalTime getOpenTo() { return openTo; }
    public void setOpenTo(LocalTime openTo) { this.openTo = openTo; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // -------------------------------------------------------------

    /** Checks if given time frame is within the room's opening times */
    public boolean isWithinOpeningTime(LocalTime from, LocalTime to) {
        return !(from.isBefore(this.openFrom) || to.isAfter(this.openTo));
    }

    private boolean isStaff(String staffId) {
        //TODO staffId check
        return true;
    }

    public void updateDetails(String staffId ,int capacity, LocalTime openFrom, LocalTime openTo) {
        if (!isStaff(staffId)) return;
        this.capacity = capacity;
        this.openFrom = openFrom;
        this.openTo = openTo;
    }

    public void updateAvailability(String staffId ,boolean active) {
        if (!isStaff(staffId)) return;
        this.active = active;
    }

    @Override
    public String toString() {
        String s =
            "Name: " + this.name
            + "\n Capacity: " + this.capacity
            + "\n Open From: " + this.openFrom
            + "\n Open To: " + this.openTo
            + "\n Active: " + this.active;

        return s;
    }
}

