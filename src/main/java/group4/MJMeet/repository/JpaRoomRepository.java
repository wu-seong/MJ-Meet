package group4.MJMeet.repository;

import group4.MJMeet.domain.Room;
import group4.MJMeet.domain.RoomMember;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
    public boolean enroll(Long roomId, String userId) { //RoomMember테이블에 등록하고 해당 Room의 table의 전체인원 카운팅
        RoomMember rm = new RoomMember();
        rm.setRoomId(roomId);
        rm.setUserId(userId);
        rm.setMondayTimetable("11111111111111111111111111111111");
        rm.setTuesdayTimetable("11111111111111111111111111111111");
        rm.setWednesdayTimetable("11111111111111111111111111111111");
        rm.setThursdayTimetable("11111111111111111111111111111111");
        rm.setFridayTimetable("11111111111111111111111111111111");
        rm.setSaturdayTimetable("11111111111111111111111111111111");
        rm.setSundayTimetable("11111111111111111111111111111111");
        //만약 이미 등록되어 있다면 등록하지 않음
        if(this.findOne(rm).isPresent()){
            return false;
        }
        em.persist(rm); //JPA가 Insert쿼리 만들어 실행
        Room room = findById(roomId).get();

        //방시간을 등록하는 것이 아니면 카운팅
        if(!(roomId.toString().equals(userId)) ){
            room.setTotalCount(room.getTotalCount()+1); //전체 인원 카운팅
            //우선시간, 여유시간 null로 초기화 (새 인원이 추가되었으므로)
        }

        return true;
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
    public List<RoomMember> getRoomMemberListByRoomId(Long roomId) {
        return   em.createQuery("select rm from RoomMember rm where rm.roomId = :roomId", RoomMember.class)
                .setParameter("roomId", roomId).getResultList().stream().filter(
                        roomMember -> !(roomMember.getRoomId().toString().equals(roomMember.getUserId())) ).collect(Collectors.toList());
    }
    @Override
    public Optional<RoomMember> setTimetable(RoomMember roomMember) {
        //모든 roomMember리스트를 가져와 userId와 roomId로 식별하여 RoomMember를 가져옴
        Optional<RoomMember> filteredRm = findAll().stream().filter( rm -> {
          return  (rm.getRoomId().equals(roomMember.getRoomId()))&&(rm.getUserId().equals(roomMember.getUserId()) );
        }).findAny();
        //가져온 RoomMember에 timeTable을 set
        if( !(roomMember.getRoomId().toString().equals(roomMember.getUserId())) &&
                filteredRm.get().isFirst()) {
            //초기값일 때만 카운팅
            //merge 테이블은 카운팅 하지 않음
            countSave(roomMember.getRoomId());
        }
        filteredRm.get().setMondayTimetable(roomMember.getMondayTimetable());
        filteredRm.get().setTuesdayTimetable(roomMember.getTuesdayTimetable());
        filteredRm.get().setWednesdayTimetable(roomMember.getWednesdayTimetable());
        filteredRm.get().setThursdayTimetable(roomMember.getThursdayTimetable());
        filteredRm.get().setFridayTimetable(roomMember.getFridayTimetable());
        filteredRm.get().setSaturdayTimetable(roomMember.getSaturdayTimetable());
        filteredRm.get().setSundayTimetable(roomMember.getSundayTimetable());
        return filteredRm;
    }
    public Optional<RoomMember> findOne(RoomMember roomMember){
        Optional<RoomMember> result = em.createQuery("select rm from RoomMember rm where rm.userId = :userId AND rm.roomId = :roomId", RoomMember.class)
                .setParameter("userId", roomMember.getUserId())
                .setParameter("roomId", roomMember.getRoomId())
                .getResultStream().findAny();
        return result;
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

    @Override
    public void countSave(Long roomId) {
        Room room = this.findById(roomId).get();
        room.setParticipantsCount(room.getParticipantsCount()+1);
    }

}
