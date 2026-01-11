package com.dimandco.proj_studroom.core.security;

import com.dimandco.proj_studroom.core.model.PersonType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Immutable view implementing Spring's {@link UserDetails} for representing a user in runtime
 */
public final class ApplicationUserDetails implements UserDetails {

    private final long personId;
    private final String emailAddress;
    private final String passwordHash;
    private final PersonType type;

    public ApplicationUserDetails(final long personId,
                                         final String emailAddress,
                                         final String passwordHash,
                                         final PersonType type) {
        if (personId <= 0) throw new IllegalArgumentException();
        if (emailAddress == null) throw new NullPointerException();
        if (emailAddress.isBlank()) throw new IllegalArgumentException();
        if (passwordHash == null) throw new NullPointerException();
        if (passwordHash.isBlank()) throw new IllegalArgumentException();
        if (type == null) throw new NullPointerException();
        this.personId = personId;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.type = type;
    }

    public long personId() {
        return this.personId;
    }

    public PersonType type() {
        return this.type;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final String role = (this.type == PersonType.STAFF) ? "ROLE_STAFF" : "ROLE_STUDENT";
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return this.emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

