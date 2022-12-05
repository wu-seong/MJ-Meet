package group4.MJMeet.repository;

import group4.MJMeet.domain.Member;
import group4.MJMeet.domain.Room;
import group4.MJMeet.domain.RoomMember;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        System.out.println(userId);
        List<RoomMember> result = em.createQuery("select rm from RoomMember rm where rm.userId = :userId", RoomMember.class).setParameter("userId", userId).getResultList();
        System.out.println(result);
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
    public List<RoomMember> findAll() {
        return  em.createQuery("select rm from RoomMember rm", RoomMember.class).getResultList();
    }

    @Override
    public List<String> findMemberIdByRoomId(Long roomId) {
        //roomId를 통해 MemberId를 List로 불러와서 반환
        List<RoomMember> filteredRm = findAll().stream().filter( roomMember -> roomMember.getRoomId().equals(roomId)).collect(Collectors.toList());
        List<String> filteredMemberId = new ArrayList<>();
        for(int i = 0; i<filteredRm.size(); i++){
            filteredMemberId.add(filteredRm.get(i).getUserId());
        }
        return filteredMemberId;
    }
}
