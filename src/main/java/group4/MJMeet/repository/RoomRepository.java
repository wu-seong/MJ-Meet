package group4.MJMeet.repository;

import group4.MJMeet.domain.Member;
import group4.MJMeet.domain.Room;
import group4.MJMeet.domain.RoomMember;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room create(Room room);
    Optional<Room> findById(Long roomId);

    List<Room> findByMember(String userId);
    void enroll(Long roomId, String id);
    String addTimetable(String timetable);
    List<RoomMember> findAll();

    List<String> findMemberIdByRoomId(Long id);
}
