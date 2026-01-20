package com.dimandco.proj_studroom.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

/** RoomReservation entity. */
@Entity
@Table//(name = "reservation")
public final class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; // Increments automatically

    @ManyToOne
    @JoinColumn
    private StudyRoom room;

    @ManyToOne
    @JoinColumn
    private Person student;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private LocalTime fromTime;

    @NotNull
    @Column(nullable = false)
    private LocalTime toTime;

    @NotNull
    @Column(nullable = false)
    private boolean active;

    public RoomReservation() {
        this.id = null;
        this.room = null;
        this.student = null;
        this.date = null;
        this.fromTime = null;
        this.toTime = null;
        this.active = false;
    }

    public RoomReservation(
            StudyRoom room,
            Person student,
            LocalDate date,
            LocalTime fromTime,
            LocalTime toTime,
            boolean active
    ) {
        this.id = null;
        this.room = room;
        this.student = student;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.active = active;
    }

   // -------------------- GETTERS AND SETTERS --------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StudyRoom getRoom() { return room; }
    public void setRoom(StudyRoom room) { this.room = room; }

    public Person getStudent() { return student; }
    public void setStudent(Person student) { this.student = student; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getFromTime() { return fromTime; }
    public void setFromTime(LocalTime fromTime) { this.fromTime = fromTime; }

    public LocalTime getToTime() { return toTime; }
    public void setToTime(LocalTime toTime) { this.toTime = toTime; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public void cancelReservation() { this.setActive(false); }

    // -------------------------------------------------------------



    private boolean isStaff(String staffId) {
        //TODO staffId check
        return true;
    }

    /** Checks if given time overlaps with its own */
    public boolean overlaps(RoomReservation rr) {
        if(!this.active) return false;
        if(!this.room.equals(rr.getRoom())) return false;
        if(!rr.getDate().equals(this.date)) return false;

        LocalTime from =  rr.getFromTime();
        LocalTime to =  rr.getToTime();

        return (from.isAfter(this.fromTime) && from.isBefore(this.toTime) ||
                to.isAfter(this.fromTime) && to.isBefore(this.toTime)) ||
                from.equals(this.fromTime) || to.equals(this.toTime);
    }

    @Override
    public String toString() {
        String s = this.date + " at " + this.fromTime + " - " +
                this.toTime + " by " + this.student.getFullName() + " at " + this.room.getName();
        return s;
    }
}


