package group4.MJMeet.service;


import group4.MJMeet.domain.Room;
import group4.MJMeet.domain.RoomMember;
import group4.MJMeet.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
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

    public RoomMember getPossible(List<RoomMember> roomMembers, RoomMember newTimetable,  int meetingTime){
        //머지테이블 초기화
        RoomMember merge;
        if(roomMembers.get(0).getUserId().equals(newTimetable.getUserId())){
            //첫 멤버테이블이 갱신될 테이블일 때
            merge = newTimetable;
        }
        else{
            merge = roomMembers.get(0);
        }
        //유저들의 테이블을 순회하면서 머지테이블 완성하기
        for(int i = 1 ;i<roomMembers.size(); i++){
            //새로운 시간표를 반영

            if( roomMembers.get(i).getUserId().equals(newTimetable.getUserId()) ){
                //System.out.println("ss");
              merge = RoomMember.mergeTimetable(merge, newTimetable);
              continue;
            }
            merge = RoomMember.mergeTimetable(merge, roomMembers.get(i) );
        }

        //머지된 시간표는 방시간표로 세팅
        merge.setRoomId(newTimetable.getRoomId());
        merge.setUserId(newTimetable.getRoomId().toString());

        return isPossible(merge, meetingTime) ? merge : null;

    }
    public boolean isPossible(RoomMember roomMember, int meetingTime){
         //요일 중 하나라도 가능한 시간을 넘기면 가능한 시간표
        return isOverTime(roomMember.getMondayTimetable(), meetingTime) ||
                isOverTime(roomMember.getTuesdayTimetable(), meetingTime) ||
                isOverTime(roomMember.getWednesdayTimetable(), meetingTime) ||
                isOverTime(roomMember.getThursdayTimetable(), meetingTime) ||
                isOverTime(roomMember.getFridayTimetable(), meetingTime) ||
                isOverTime(roomMember.getSaturdayTimetable(), meetingTime) ||
                isOverTime(roomMember.getSundayTimetable(), meetingTime)
            ? true : false;
    }
    public boolean isOverTime(String timetable, int meetingTime){
        int maxLength = 0;
        int length = 0;
        boolean[] boolTimetable = RoomMember.changeBool(timetable);
        for(int i = 0; i<boolTimetable.length; i++){
            if(boolTimetable[i]){
                length++;
                maxLength = Integer.max(maxLength, length);
            }
            else{
                length = 0;
            }
        }
        System.out.println("maxLength: " + maxLength);
        return maxLength >= meetingTime*2 ? true : false;
    }
    public RoomMember saveTimetable(RoomMember roomMember){

        //roomId로 roomMember리스트 불러오기
        List<RoomMember> roomMembers = roomRepository.getRoomMemberListByRoomId(roomMember.getRoomId());
        //roomMember, timetable, meetingTime
        //roomId에 있는 RoomMember의 시간 표를 다시 계산하여 meetingTime보다 큰 시간이 있는지 판단 (이때 새로운 시간표를 반영하여 계산)
        RoomMember mergedTimetable = getPossible(roomMembers, roomMember, roomRepository.findById(roomMember.getRoomId()).get().getMeetingTime());
        if(mergedTimetable == null){
            mergedTimetable = new RoomMember();
            mergedTimetable.setId(-1L);
            return mergedTimetable;
        }
        //가능한 시간이 있으면 카운팅하고 , RoomMember에 새로운 시간표와 머지된 방 시간표 갱신 저장, 아니면 그대로 예외처리
        //카운팅한 인원이 전체 인원과 같으면 우선시간과 여유시간 계산하여 저장
        roomRepository.setTimetable(roomMember);
        roomRepository.setTimetable(mergedTimetable);

        return mergedTimetable;
    }
    public RoomMember findRoomTimetable(Long roomId){
        RoomMember temp = new RoomMember();
        temp.setRoomId(roomId);
        temp.setUserId(roomId.toString());
        return roomRepository.findOne(temp).get();
    }

    public boolean enrollRoomMember(Long roomId, String userId){
        return roomRepository.enroll(roomId, userId);
    }
}
