package group4.MJMeet.domain;

import lombok.Getter;
import lombok.Setter;

//시간정보를 주고 받기 위한 객체
@Getter
@Setter
public class Timetable {
    private String userId;
    private Long roomId;
    private String mondayTimetable;
    private String tuesdayTimetable;
    private String wednesdayTimetable;
    private String thursdayTimetable;
    private String fridayTimetable;
    private String saturdayTimetable;
    private String sundayTimetable;
}
