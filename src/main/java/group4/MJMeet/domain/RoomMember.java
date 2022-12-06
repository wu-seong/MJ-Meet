package group4.MJMeet.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;

//    @ManyToOne
//    private Room room;

    private Long roomId;
    private String mondayTimetable;
    private String tuesdayTimetable;
    private String wednesdayTimetable;
    private String thursdayTimetable;
    private String fridayTimetable;
    private String saturdayTimetable;
    private String sundayTimetable;
}
