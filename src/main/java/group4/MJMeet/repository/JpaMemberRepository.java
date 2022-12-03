package group4.MJMeet.repository;

import group4.MJMeet.domain.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository (EntityManager em){
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member); //JPA가 Insert쿼리 만들어 실행
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

//    @Override
//    public Optional<Member> findByName(String name) { //DB에서 Member Entity를 순회하여 매칭되는 name을 찾는다.
////        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
////        return result.stream().findAny();
//    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
