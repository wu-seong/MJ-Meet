package group4.MJMeet.repository;

import group4.MJMeet.domain.Member;
import group4.MJMeet.domain.Room;
import group4.MJMeet.domain.RoomMember;
import group4.MJMeet.domain.Timetable;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room create(Room room);
    Optional<Room> findById(Long roomId);

    List<Room> findByMember(String userId);
    void enroll(Long roomId, String id);
    String addTimetable(String timetable);
    List<RoomMember> findAll();

    Optional<RoomMember> insertTimetable(String userId, Long roomId, Timetable timetable);
    List<String> findMemberIdByRoomId(Long roomId);

    void countSave(Long roomId);
}
