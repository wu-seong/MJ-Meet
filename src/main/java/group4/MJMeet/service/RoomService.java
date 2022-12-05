package group4.MJMeet.service;


import group4.MJMeet.domain.Room;
import group4.MJMeet.domain.RoomMember;
import group4.MJMeet.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Optional<Room> findRoom(Long roomId){ //roomId로 방을 조회하는 메소드
        return roomRepository.findById(roomId);
    }
    public List<Room> findRooms(String userId){
        List<RoomMember> rm =  roomRepository.findAll().stream().filter( (roomMember) -> {
            return roomMember.getUserId().equals(userId);
        }).collect(Collectors.toList());
        //가져온 RoomMember 객체의 RoomId를 이용하여 Room가져와 리스트로 만들기
        List<Room> filteredRoom = new LinkedList<>();
        for(int i = 0; i<rm.size(); i++){
            filteredRoom.add( roomRepository.findById(rm.get(i).getRoomId()).get() );
        }
        return filteredRoom;
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
    public List<String> MemberIds(Long roomId){
        return roomRepository.findMemberIdByRoomId(roomId);
    }

}
