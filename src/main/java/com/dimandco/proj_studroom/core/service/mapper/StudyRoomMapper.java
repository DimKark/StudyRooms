package com.dimandco.proj_studroom.core.service.mapper;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.service.model.StudyRoomView;
import org.springframework.stereotype.Component;

@Component
public class StudyRoomMapper {
    public StudyRoomView convertStudyRoomToStudyRoomView(final StudyRoom studyRoom) {
        if(studyRoom == null) return null;

        final StudyRoomView studyRoomView = new StudyRoomView(
                studyRoom.getId(),
                studyRoom.getName(),
                studyRoom.getCapacity(),
                studyRoom.getOpenFrom(),
                studyRoom.getOpenTo(),
                studyRoom.getActive()
        );

        return studyRoomView;
    }
}
