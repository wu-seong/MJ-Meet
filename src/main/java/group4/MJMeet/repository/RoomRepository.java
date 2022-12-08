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
    boolean enroll(Long roomId, String id);
    String addTimetable(String timetable);
    List<RoomMember> findAll();

    List<RoomMember> getRoomMemberListByRoomId(Long roomId);

    Optional<RoomMember> setTimetable(RoomMember roomMember);
    List<String> findMemberIdByRoomId(Long roomId);


    void countSave(Long roomId);

    public Optional<RoomMember> findOne(RoomMember roomMember);
}
