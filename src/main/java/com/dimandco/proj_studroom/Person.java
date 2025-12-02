package com.dimandco.proj_studroom;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

/**
 * Person entity.
 */

@Entity
@Table(name = "person", uniqueConstraints = {}, indexes = {
        @Index(name = "idx_person_type", columnList = "type")
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; // Increments automatically

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private PersonType type;

    public Person() {
        this.id = null;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phoneNumber = "";
        this.createdAt = Instant.now();
        this.type = PersonType.STUDENT; // Default value can't be null because the field is not nullable
    }

    public Person(String firstName, String lastName, String email,
                  String phoneNumber, String s, String string, PersonType type) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = Instant.now();
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "\nName: " + firstName + " " + lastName
                + "\nEmail: " + email
                + "\nPhone: " + phoneNumber
                + "\nCreated at: " + createdAt.toString()
                + "\nClassification:" + type;
    }
}
