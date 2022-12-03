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
    private int meetingTime;
    private int totalCount;
    private int participantsCount = 0;
    private String timetable;
}