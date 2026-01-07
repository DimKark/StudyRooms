package com.dimandco.proj_studroom.core.port.impl.dto;

import com.dimandco.proj_studroom.core.model.PersonType;

/**
 * LookupResult DTO
 */
public record LookupResult(String raw,
                           boolean exists,
                           String huaId,
                           PersonType type) {
}
