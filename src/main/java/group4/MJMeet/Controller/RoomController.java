package group4.MJMeet.Controller;

import group4.MJMeet.domain.Room;
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

        roomService.createAndEnroll(room, userId);
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
    public String getRoomTimetable(@RequestBody Long roomId){
        Optional<Room> room = roomService.findRoom(roomId);
        return room.get().getTimetable();
    }
    @PostMapping("api/roomInfo")
    @ResponseBody
    public Room getRoomInfo(@RequestBody Long roomId){
        Optional<Room> room = roomService.findRoom(roomId);
        return room.get();
    }
}
