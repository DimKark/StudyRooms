package com.dimandco.proj_studroom.core.port;

import com.dimandco.proj_studroom.core.model.PersonType;

import java.util.Optional;

/**
 * Port to external service for managing Hua ID validations.
 */
public interface HuaIdValidationPort {

    boolean validate(final String huaId);
}
