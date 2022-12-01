package group4.MJMeet.Controller;

import group4.MJMeet.domain.Member;
import group4.MJMeet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @CrossOrigin(origins = "http://localhost:3000") //CROS 해결을 위한 origin 추가
    @PostMapping("/api/signup")
    @ResponseBody
    public Member createMember(@RequestBody Member member){
        Member m = null;
        try{
            //성공하면 멤버 객체 반환
            memberService.join(member);
            m = memberService.findOne(member.getId()).get();
        }
        catch (IllegalStateException e){
            Logger.getGlobal().info("중복 오류");
        }
        return m; //성공하면 맴버 객체를 실패하면 null 반환
    }

    //@PostMapping("/signin")

}

