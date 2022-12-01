package group4.MJMeet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Member {
    @Id //pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 자동으로 ID 지정
    private Long id;
    private String name;
    private String passwd;


}
