package group4.MJMeet.config;

import group4.MJMeet.repository.JpaMemberRepository;
import group4.MJMeet.repository.JpaRoomRepository;
import group4.MJMeet.repository.MemberRepository;
import group4.MJMeet.repository.RoomRepository;
import group4.MJMeet.service.MemberService;
import group4.MJMeet.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

    @Bean
    public RoomService RoomService(){
        return new RoomService(roomRepository());
    }
    @Bean
    public RoomRepository roomRepository(){
        return new JpaRoomRepository(em);
    }

}
