package group4.MJMeet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private List<RoomMember> getRoommemberTimetable() {
        return roomMemberList;
    }

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<RoomMember> roomMemberList;
}