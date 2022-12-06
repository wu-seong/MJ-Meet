package group4.MJMeet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RoomId;
    private String RoomName;
    private int meetingTime;
    private int totalCount;
    private int participantsCount = 0;
    private String mondayTimetable;
    private String tuesdayTimetable;
    private String wednesdayTimetable;
    private String thursdayTimetable;
    private String fridayTimetable;
    private String saturdayTimetable;
    private String sundayTimetable;

//    public List<RoomMember> getRoomMemberList() {
//        return roomMemberList;
//    }
//
//    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
//    private List<RoomMember> roomMemberList;
//}

}