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
}
