package com.dimandco.proj_studroom;

public record PersonView (
        long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        PersonType type
) {
    public String fullName() {
        return this.firstName + " " + this.lastName;
    }
}
