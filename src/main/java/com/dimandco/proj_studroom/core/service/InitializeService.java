package com.dimandco.proj_studroom.core.service;

import com.dimandco.proj_studroom.PersonService;
import com.dimandco.proj_studroom.core.model.PersonType;
import com.dimandco.proj_studroom.core.service.model.CreatePersonRequest;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class InitializeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializeService.class);

    private final PersonService personService;
    private final AtomicBoolean initialized;

    public InitializeService(final PersonService personService) {
        if (personService == null) throw new NullPointerException();
        this.personService = personService;
        this.initialized = new AtomicBoolean(false);
    }

    @PostConstruct
    public void populateDatabaseWithInitialData() {
        final boolean alreadyInitialized = this.initialized.getAndSet(true);
        if (alreadyInitialized) {
            LOGGER.warn("Database initialization skipped: initial data already exist");
            return;
        }
        LOGGER.info("Starting database initialization...");
        final List<CreatePersonRequest> createPersonRequestList = List.of(
                new CreatePersonRequest(
                        PersonType.STAFF,
                        "t0001",
                        "JOHN",
                        "DOE",
                        "jd@hua.gr",
                        "+306900000000",
                        "1234"
                ),
                new CreatePersonRequest(
                        PersonType.STUDENT,
                        "it2023001",
                        "Test 1",
                        "Test 1",
                        "it2023001@hua.gr",
                        "+306900000001",
                        "1234"
                ),
                new CreatePersonRequest(
                        PersonType.STUDENT,
                        "it2023002",
                        "Test 2",
                        "Test 2",
                        "it2023002@hua.gr",
                        "+306900000002",
                        "1234"
                )
        );
        for (final var createPersonRequest : createPersonRequestList) {
            this.personService.createPerson(createPersonRequest, false); // do not send SMS
        }
        LOGGER.info("Database initialization completed.");
    }
}
