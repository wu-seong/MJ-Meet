package group4.MJMeet.repository;

import group4.MJMeet.domain.Member;
import group4.MJMeet.domain.Room;
import group4.MJMeet.domain.RoomMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaRoomRepository implements RoomRepository {
    private final EntityManager em;

    public JpaRoomRepository (EntityManager em){
        this.em = em;
    }


    @Override
    public Room create(Room room) { //방 객체 받아 만들어 Insert
        em.persist(room); //JPA가 Insert쿼리 만들어 실행
        return room;
    }

    @Override
    public Optional<Room> findById(Long roomId) { //RoomId로 방 찾기
        Room room = em.find(Room.class, roomId);
        return Optional.ofNullable(room);
    }

    @Override
    public List<Room> findByMember(String userId) { //RoomMember테이블 순회하며 해당 유저Id가 등록된 모든 방 찾기
        List<RoomMember> result = em.createQuery("select rm from RoomMember rm where rm.userId = :userId", RoomMember.class).setParameter("userId", userId).getResultList();
        return null;
    }


    @Override
    public void enroll(Long roomId, String userId) { //RoomMember테이블에 등록하고 해당 Room의 table의 전체인원 카운팅
        RoomMember rm = new RoomMember();
        rm.setRoomId(roomId);
        rm.setUserId(userId);
        em.persist(rm); //JPA가 Insert쿼리 만들어 실행
        Room room = findById(roomId).get();
        room.setTotalCount(room.getTotalCount()+1); //전체 인원 카운팅
    }

    @Override
    public String addTimetable(String timetable) {
        return null;
    }

    @Override
    public List<Room> findAll() {
        return null;
    }
}
