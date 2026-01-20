package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import java.time.LocalTime;

public record ModifyStudyRoomRequest(
        Long roomId,
        int capacity,
        LocalTime openFrom,
        LocalTime openTo,
        boolean toggleActive
) {
    public static ModifyStudyRoomRequest empty() {
        return new ModifyStudyRoomRequest(null, 0, null, null, false);
    }
}
