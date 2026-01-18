package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.ValidationFailReason;
import com.dimandco.proj_studroom.core.port.impl.dto.HuaIdValidationResult;

/**
 * Similar to {@link HuaIdValidationResult} but for use in internal operations
 */
public record InternalHuaIdValidationResult(
        boolean valid,
        ValidationFailReason reason
) {
    public static InternalHuaIdValidationResult success() {
        return new InternalHuaIdValidationResult(true, null);
    }

    public static InternalHuaIdValidationResult fail(ValidationFailReason reason) {
        return new InternalHuaIdValidationResult(false, reason);
    }
}
