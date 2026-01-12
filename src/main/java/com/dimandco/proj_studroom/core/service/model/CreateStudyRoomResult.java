package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.StudyRoom;

public record CreateStudyRoomResult (
        boolean created,
        String reason,
        StudyRoomView studyRoomView
) {
    public static CreateStudyRoomResult success(final StudyRoomView studyRoomView) {
        if(studyRoomView == null) throw new NullPointerException("studyRoomView cannot be null");
        return new CreateStudyRoomResult(true, null, studyRoomView);
    }

    public static CreateStudyRoomResult failure(final String reason) {
        if(reason == null) throw new NullPointerException("reason cannot be null");
        if(reason.isBlank()) throw new IllegalArgumentException("reason cannot be empty");

        return new CreateStudyRoomResult(false, reason, null);
    }
}
