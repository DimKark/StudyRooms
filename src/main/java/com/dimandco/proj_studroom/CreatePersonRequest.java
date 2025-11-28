package com.dimandco.proj_studroom;

public record CreatePersonRequest(
        PersonType type,
        String id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
){
}
