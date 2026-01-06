package com.dimandco.proj_studroom.core.security;

import com.dimandco.proj_studroom.PersonType;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @see CurrentUserProvider
 */
public record CurrentUser (long id , String emailAddress, PersonType personType) {}
