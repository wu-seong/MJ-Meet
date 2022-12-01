package group4.MJMeet.service;

import group4.MJMeet.domain.Member;
import group4.MJMeet.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){ //회원을 추가하는 메소드
        //중복회원 체크
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //회원 중복을 체크하는 메소드
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){ //ID로 회원을 조회하는 메소드
        return memberRepository.findById(memberId);
    }
}
