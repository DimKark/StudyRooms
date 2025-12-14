package com.dimandco.proj_studroom.port.impl;

import com.dimandco.proj_studroom.config.RestApiClientConfig;
import com.dimandco.proj_studroom.port.SmsNotificationPort;
import com.dimandco.proj_studroom.port.impl.dto.SendSmsRequest;
import com.dimandco.proj_studroom.port.impl.dto.SendSmsResult;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Default implementation of {@link SmsNotificationPort}. It uses the Noc external service
 */
@Service
public class SmsNotificationPortImpl implements SmsNotificationPort {

    private final RestTemplate restTemplate;

    public SmsNotificationPortImpl(final RestTemplate restTemplate) {
        if (restTemplate == null) throw new NullPointerException();
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean sendSms(final String e164, final String content) {
        if (e164 == null) throw new NullPointerException();
        if (e164.isBlank()) throw new IllegalArgumentException();
        if (content == null) throw new NullPointerException();
        if (content.isBlank()) throw new IllegalArgumentException();

        //HTTP HEADERS
        // -----------------------------------

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // -----------------------------------

        final SendSmsRequest body = new SendSmsRequest(e164, content);

        // -----------------------------------

        final String baseurl = RestApiClientConfig.BASE_URL;
        final String url = baseurl + "/api/v1/sms";
        final HttpEntity<SendSmsRequest> entity = new HttpEntity<>(body, httpHeaders); // request
        final ResponseEntity<SendSmsResult> response = this.restTemplate.postForEntity(url, entity, SendSmsResult.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            final SendSmsResult sendsmsresult = response.getBody();
            if (sendsmsresult != null) throw new NullPointerException();
            return sendsmsresult.sent();
        }

        throw new RuntimeException("External service responded with " + response.getStatusCode());
    }
}
