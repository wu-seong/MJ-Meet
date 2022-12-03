package group4.MJMeet.service;

import group4.MJMeet.domain.Member;
import group4.MJMeet.domain.Room;
import group4.MJMeet.repository.MemberRepository;
import group4.MJMeet.repository.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class RoomServiceTest {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomRepository roomRepository;

    @Test
    void findRoom() {
        //given
        Room r = new Room();
        r.setMeetingTime(2);
        r.setParticipantsCount(1);
        r.setTimetable("01110101");
        Long saveId = roomService.createAndEnroll(r, "60192234");
        //then
//        Member findMember = memberService.findOne(saveId).get();
//        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void mergeTimetable() {
    }

    @Test
    void createAndEnroll() {
    }
}