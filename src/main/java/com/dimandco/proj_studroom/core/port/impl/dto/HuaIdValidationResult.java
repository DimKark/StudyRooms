package com.dimandco.proj_studroom.core.port.impl.dto;

import com.dimandco.proj_studroom.core.model.PersonType;

/**
 * HuaIdValidationResult DTO (Specifically to match external service's HuaIdValidationResult class)
 */
public record HuaIdValidationResult(
        boolean valid,
        PersonType type
) {}
