package group4.MJMeet.Controller;

import group4.MJMeet.domain.Room;
import group4.MJMeet.domain.RoomMember;
import group4.MJMeet.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") //CROS 해결을 위한 origin 추가
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/api/room")
    @ResponseBody
    public Room createRoom(@RequestBody HashMap<String, Object> para) {
        //요청 값 받아오기
        String userId = (String)para.get("id");
        Integer meetingTime = Integer.parseInt((String)(para.get("meetingTime")));
        String roomName = (String)para.get("roomName");

        Room room = new Room();
        room.setRoomName(roomName);
        room.setMeetingTime(meetingTime);
        //얻은 방 ID를 UserID로 등록하여 방 시간표 추가
        Long createdRoomId = roomService.createAndEnroll(room, userId);
        roomService.enrollRoomMember(createdRoomId, createdRoomId.toString());
        return room;
    }

    @PostMapping("/api/rooms")
    @ResponseBody
    public Room[] lookupRoom(@RequestBody String userId){
        //userId 받아오기
        String uid = userId.substring(0,userId.length()-1).substring(1);
        //userId 입력하여 유저가 속한 방 정보 가져와 List에 저장
        List<Room> filteredRoom  = roomService.findRooms(uid);
        return filteredRoom.toArray(new Room[filteredRoom.size()]);
    }

    @PostMapping("api/roomTimetable")
    @ResponseBody
    public String[] getRoomTimetable(@RequestBody Long roomId){
        RoomMember roomTimetable = roomService.findRoomTimetable(roomId);
        //각 요일마다의 타임테이블 정보를 담는 스트링 배열
        String[] timeTable = new String[7];
        timeTable[0] = roomTimetable.getMondayTimetable();
        timeTable[1] = roomTimetable.getTuesdayTimetable();
        timeTable[2] = roomTimetable.getWednesdayTimetable();
        timeTable[3] = roomTimetable.getThursdayTimetable();
        timeTable[4] = roomTimetable.getFridayTimetable();
        timeTable[5] = roomTimetable.getSaturdayTimetable();
        timeTable[6] = roomTimetable.getSundayTimetable();
        return timeTable;
    }
    @PostMapping("api/roomInfo")
    @ResponseBody
    public Room getRoomInfo(@RequestBody Long roomId){
        Optional<Room> room = roomService.findRoom(roomId);
        return room.get();
    }

    @PostMapping("api/timetable")
    @ResponseBody
    public RoomMember save(@RequestBody RoomMember roomMember){
        //userId와 roomId로 접근하여
        //각 요일에 시간 정보 넣기
        return roomService.saveTimetable(roomMember);
    }

    @PostMapping("api/room/user")
    @ResponseBody
    //RoomMember에 유저 등록
    public Room enrollRoom(@RequestBody HashMap<String, Object> para){
        String userId = (String)para.get("userId");
        Long roomId =  Long.parseLong((String)para.get("roomId"));
        //중복이라 등록 하지 못하면 id -1인 룸객체 반환
        if(!roomService.enrollRoomMember(roomId, userId)){
            Room room = new Room();
            room.setRoomId(-1L);
            return room;
        };
        //등록하면 중복한 룸객체 반환
        return roomService.findRoom(roomId).get();
    }
    @PostMapping("api/priorityTime")
    @ResponseBody
    public String getPriorityResult(@RequestBody HashMap<String, Object> para){
        Long roomId =  Long.parseLong((String)para.get("roomId"));
        //룸id로 방 정보 현재 참여한 인원이 전체 인원보다 적으면 -1반환
        Room room = roomService.findRoom(roomId).get();
        if(!roomService.saveAll(room)){ //
            return "-1";
        }
        int meetingTime = room.getMeetingTime();
        //아니면
        //룸id로 RoomMember에 있는 방 시간표와 meetingTime을 가져오기
        RoomMember roomTimetable = roomService.findRoomTimetable(roomId);
        String result = RoomMember.hashToString(RoomMember.firstTime(roomTimetable, meetingTime));
        //연산한 텍스트 가저와서 넘겨주기
        return result;
    }

    @PostMapping("api/longTime")
    @ResponseBody
    public String getLongResult(@RequestBody Long roomId){

        //룸id로 방 정보 현재 참여한 인원이 전체 인원보다 적으면 -1반환
        Room room = roomService.findRoom(roomId).get();
        if(!roomService.saveAll(room)){ //
            return "-1";
        }
        int meetingTime = room.getMeetingTime();
        //아니면
        //룸id로 RoomMember에 있는 방 시간표와 meetingTime을 가져오기
        RoomMember roomTimetable = roomService.findRoomTimetable(roomId);
        //String result = calculateLong(roomTimetable, meetingTime);
        //연산한 텍스트 가저와서 넘겨주기
        //return result;
        return "-1";
    }
}
