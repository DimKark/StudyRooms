package com.dimandco.proj_studroom.core.service;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.service.model.CreateStudyRoomRequest;
import com.dimandco.proj_studroom.core.service.model.CreateStudyRoomResult;

/** Service for managing {@link StudyRoom}s */
public interface StudyRoomService {
    public CreateStudyRoomResult createStudyRoom(CreateStudyRoomRequest request);
}
