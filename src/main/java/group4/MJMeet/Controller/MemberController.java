package group4.MJMeet.Controller;

import group4.MJMeet.domain.Member;
import group4.MJMeet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") //CROS 해결을 위한 origin 추가
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/api/signup")
    @ResponseBody
    public Member createMember(@RequestBody Member member){
            //성공하면 멤버 객체 반환
        try {
            memberService.join(member);
        }
        catch (Exception e){
            member = new Member();
            member.setId("-1"); //실패하면 id가 -1인 빈 맴버 객체를 반환
        }
        return member;
    }

    @PostMapping("/api/login")
    @ResponseBody
    public Member login(@RequestBody Member member) {
        //log.info("user email = {}", user.get("email"));
        //id가 유효한지 확인
        if(!memberService.findOne(member.getId()).isPresent()) {
            //유효하지 않은 id이면 id에 -1가진 맴버 객체 반환
            new IllegalArgumentException("가입되지 않은 id 입니다.");
            member = new Member();
            member.setId("-1");
            return member;
        }
        else{
            //일치하면 객체 반환
            if(memberService.checkPasswd(member.getId(), member.getPasswd())){
                return memberService.findOne(member.getId()).get();
            }
            //일치하지 않으면 id에 -2가진 맴버 객체 반환
            else{
                new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
                member = new Member();
                member.setId("-2");
                return member;
            }
        }
    }


}


