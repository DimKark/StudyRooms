package com.dimandco.proj_studroom.core.port.impl;

import com.dimandco.proj_studroom.config.RestApiClientConfig;
import com.dimandco.proj_studroom.core.model.PersonType;
import com.dimandco.proj_studroom.core.model.ValidationFailReason;
import com.dimandco.proj_studroom.core.port.HuaIdValidationPort;
import com.dimandco.proj_studroom.core.port.impl.dto.HuaIdValidationResult;
import com.dimandco.proj_studroom.core.service.model.InternalHuaIdValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Default implementation of {@link HuaIdValidationPort}. It uses the Noc external service
 */
@Service
public class HuaIdValidationPortImpl implements HuaIdValidationPort {

    private final RestTemplate restTemplate;

    public HuaIdValidationPortImpl(final RestTemplate restTemplate) {
        if (restTemplate == null) throw new NullPointerException();
        this.restTemplate = restTemplate;
    }

    @Override
    public InternalHuaIdValidationResult validate(final String huaId, PersonType type) {
        if (huaId == null) throw new NullPointerException();
        if (huaId.isBlank()) throw new IllegalArgumentException();

        final String baseUrl = RestApiClientConfig.BASE_URL;
        final String url = baseUrl + "/api/v1/verifyhuaid/" + huaId;
        final ResponseEntity<HuaIdValidationResult> response =
                this.restTemplate.getForEntity(url, HuaIdValidationResult.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            final HuaIdValidationResult huaIdValidationResult = response.getBody();
            if (huaIdValidationResult == null) throw new NullPointerException();

            if(huaIdValidationResult.valid()) {
               if(type == huaIdValidationResult.type()) return InternalHuaIdValidationResult.success();
               else return InternalHuaIdValidationResult.fail(ValidationFailReason.INVALID_TYPE);
            }
            else return InternalHuaIdValidationResult.fail(ValidationFailReason.INVALID_HUA_ID);
        }
        throw new RuntimeException("External service responded with " + response.getStatusCode());
    }
}
