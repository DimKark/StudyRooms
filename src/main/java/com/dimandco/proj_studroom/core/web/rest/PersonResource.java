package com.dimandco.proj_studroom.core.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.dimandco.proj_studroom.PersonService;
import com.dimandco.proj_studroom.core.service.model.PersonView;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/** Rest Controler for the person resource **/

@RestController
@RequestMapping(value="/api/v1/person",  produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonResource {

    private final PersonService personDataService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PersonResource(final PersonService personDataService) {
        if (personDataService == null) throw new NullPointerException();
        this.personDataService = personDataService;
    }

    @PreAuthorize("hasRole('INTEGRATION_READ')")
    @GetMapping
    public List<PersonView> people() {
        final List<PersonView> personViewList = this.personDataService.getPeople();
        return personViewList;
    }

    @GetMapping("/all_people")
    public String peopleManualJson() throws IOException {
        final List<PersonView> personViewList = this.personDataService.getPeople();
        final StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, personViewList);
        final String jsonString = stringWriter.toString();
        return jsonString;
    }

}
