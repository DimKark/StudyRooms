package com.dimandco.proj_studroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findById(final String id);

    List<Person> findAllByTypeOrderByLastName(final PersonType type);

    boolean existsByEmailAddressIgnoreCase(final String email);

    boolean existsByMobilePhoneNumber(final String phoneNumber);
}
