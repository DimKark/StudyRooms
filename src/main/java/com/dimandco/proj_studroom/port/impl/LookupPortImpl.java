package com.dimandco.proj_studroom.port.impl;

import com.dimandco.proj_studroom.PersonRepository;
import com.dimandco.proj_studroom.PersonType;
import com.dimandco.proj_studroom.config.RestApiClientConfig;
import com.dimandco.proj_studroom.port.LookupPort;
import com.dimandco.proj_studroom.port.impl.dto.LookupResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
/**
 * Default implementation of {@link LookupPort}. It uses the Noc external service
 * commented out stuff because we don't have the url
 */
@Service
public class LookupPortImpl implements LookupPort {

    private final RestTemplate restTemplate;

    public LookupPortImpl(final RestTemplate restTemplate) {
        if (restTemplate == null) throw new NullPointerException();
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<PersonType> lookup(final String huaId) {
        //if (huaId == null) throw new NullPointerException();
        //if (huaId.isBlank()) throw new IllegalArgumentException();

        //final String baseUrl = RestApiClientConfig.BASE_URL;
        //final String url = baseUrl + "/api/v1/lookups/" + huaId;
        //final ResponseEntity<LookupResult> response = this.restTemplate.getForEntity(url, LookupResult.class);

        /**if (response.getStatusCode().is2xxSuccessful()) {
            final LookupResult lookupResult = response.getBody();
            if (lookupResult == null) throw new NullPointerException();
            return Optional.ofNullable(lookupResult.type());
        }*/
        //throw new RuntimeException("External service responded with " + response.getStatusCode());
        return Optional.empty();
    }
}
