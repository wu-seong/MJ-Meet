package group4.MJMeet.User;

import org.springframework.data.jpa.repository.JpaRepository;

//엔티티의 타입(Question)과 해당 엔티티의 PK의 속성 타입(Integer)을 지정해야 한다.
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}