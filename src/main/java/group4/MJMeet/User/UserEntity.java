package group4.MJMeet.User;

import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserEntity {

    @Id //id 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터를 저장할 때 해당 속성에 값을 따로 세팅하지 않아도 1씩 자동으로 증가하
    private Integer id;

    @Column//테이블 컬럼 어노테이션
    private String password;

    @Column
    private String username;

}