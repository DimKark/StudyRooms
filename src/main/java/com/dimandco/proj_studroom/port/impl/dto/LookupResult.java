package com.dimandco.proj_studroom.port.impl.dto;

import com.dimandco.proj_studroom.PersonType;

/**
 * LookupResult DTO
 */
public record LookupResult(String raw,
                           boolean exists,
                           String huaId,
                           PersonType type) {
}
