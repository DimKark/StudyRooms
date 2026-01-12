package com.dimandco.proj_studroom.core.security;

import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of Spring's {@link UserDetailsService} for providing application users
 */
@Service
public final class ApplicationUserDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    public ApplicationUserDetailsService(final PersonRepository personRepository) {
        if (personRepository == null) throw new NullPointerException();
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (username == null) throw new NullPointerException("Username is null");
        if (username.isBlank()) throw new IllegalArgumentException("Username cannot be blank");
        final Person person = this.personRepository
                .findByEmailAddressIgnoreCase(username)
                .orElse(null);
        if (person == null) {
            throw new UsernameNotFoundException("person with emailAddress" + username + " does not exist");
        }
        return new ApplicationUserDetails(
                person.getId(),
                person.getEmailAddress(),
                person.getPasswordHash(),
                person.getType()
        );
    }
}