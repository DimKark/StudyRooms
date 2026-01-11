package com.dimandco.proj_studroom.core.security;

import com.dimandco.proj_studroom.core.model.PersonType;

/**
 * @see CurrentUserProvider
 */
public record CurrentUser (long id , String emailAddress, PersonType type) {}
