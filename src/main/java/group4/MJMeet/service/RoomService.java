package group4.MJMeet.service;

import group4.MJMeet.domain.Member;
import group4.MJMeet.domain.Room;
import group4.MJMeet.repository.MemberRepository;
import group4.MJMeet.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Optional<Room> findOne(Long roomId){ //roomId로 방을 조회하는 메소드
        return roomRepository.findById(roomId);
    }
    public String mergeTimetable(Long roomId ,String addedTimetable){ //기존 타임 테이블과 새로운 타임 테이블을 병합
        String result = addedTimetable;
        //roomId로 타임테이블 가져오기
        //병합하기
        //roomId의 최소시간으로 유효성 판별
        //만약 병합한 결과가 유효(지정된 시간 이하)하지 않다면 예외 처리
        return result;
    }

    public Long createAndEnroll(Room room, String userId){ //방을 추가하고 방에 유저 등록
       roomRepository.create(room);
       roomRepository.enroll(room.getRoomId(), userId);
        return room.getRoomId();
    }

}
