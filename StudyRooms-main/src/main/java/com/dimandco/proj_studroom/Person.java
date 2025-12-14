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

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "mobilePhone_number")
    private String mobilePhoneNumber;

    @Column(name = "rawPassword")
    private String passwordHash;

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
        this.emailAddress = "";
        this.mobilePhoneNumber = "";
        this.passwordHash = "";
        this.createdAt = Instant.now();
        this.type = PersonType.STUDENT; // Default value can't be null because the field is not nullable
    }

    public Person(String firstName, String lastName, String emailAddress,
                  String mobilePhoneNumber, String passwordhash, PersonType type) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.passwordHash = passwordhash;
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

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() { return emailAddress; }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

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
                + "\nEmail: " + emailAddress
                + "\nPhone: " + mobilePhoneNumber
                + "\nCreated at: " + createdAt.toString()
                + "\nClassification:" + type;
    }
}
