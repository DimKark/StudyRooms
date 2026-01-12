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
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @NotBlank
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

    /** Checks if given time frame is within the room's opening times */
    public boolean isWithinOpeningTime(LocalTime from, LocalTime to) {
        return !(from.isBefore(this.openFrom) || to.isAfter(this.openTo));
    }
}

