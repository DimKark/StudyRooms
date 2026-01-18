package com.dimandco.proj_studroom.core.port;

import com.dimandco.proj_studroom.core.model.PersonType;
import com.dimandco.proj_studroom.core.service.model.InternalHuaIdValidationResult;

import java.util.Optional;

/**
 * Port to external service for managing Hua ID validations.
 */
public interface HuaIdValidationPort {

    InternalHuaIdValidationResult validate(final String huaId, PersonType type);
}
