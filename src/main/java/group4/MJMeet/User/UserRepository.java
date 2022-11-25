package group4.MJMeet.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//엔티티의 타입(Question)과 해당 엔티티의 PK의 속성 타입(Integer)을 지정해야 한다.
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByPassword(String password);
    UserEntity findByPasswordAndUsername(String password, String username);
    List<UserEntity> findByPasswordLike(String password);
    Page<UserEntity> findAll(Pageable pageable);
}