package com.dimandco.proj_studroom.core.service.impl;

import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.repository.StudyRoomRepository;
import com.dimandco.proj_studroom.core.service.StudyRoomService;
import com.dimandco.proj_studroom.core.service.mapper.StudyRoomMapper;
import com.dimandco.proj_studroom.core.service.model.CreateStudyRoomRequest;
import com.dimandco.proj_studroom.core.service.model.CreateStudyRoomResult;
import com.dimandco.proj_studroom.core.service.model.StudyRoomView;
import org.springframework.stereotype.Service;

@Service
public class StudyRoomServiceImpl implements StudyRoomService {
    private final StudyRoomMapper studyRoomMapper;
    private final StudyRoomRepository studyRoomRepository;

    public StudyRoomServiceImpl(StudyRoomMapper studyRoomMapper, StudyRoomRepository studyRoomRepository) {
        if(studyRoomMapper == null) throw new NullPointerException("reservationMapper cannot be null");
        if(studyRoomRepository == null) throw new NullPointerException("studyRoomRepository cannot be null");
        this.studyRoomMapper = studyRoomMapper;
        this.studyRoomRepository = studyRoomRepository;
    }

    @Override
    public CreateStudyRoomResult createStudyRoom(CreateStudyRoomRequest csrr) {
        StudyRoom studyRoom = new StudyRoom();

        studyRoom.setName(csrr.name());
        studyRoom.setCapacity(csrr.capacity());
        studyRoom.setOpenFrom(csrr.openFrom());
        studyRoom.setOpenTo(csrr.openTo());
        studyRoom.setActive(csrr.active());

        // -------------------------------------------

        studyRoom = this.studyRoomRepository.save(studyRoom);

        final StudyRoomView studyRoomView = this.studyRoomMapper.convertStudyRoomToStudyRoomView(studyRoom);

        return CreateStudyRoomResult.success(studyRoomView);
    }
}
