package group4.MJMeet.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member {
     //pk 설정
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 자동으로 ID 지정
    @Id
    @Column(name = "id")
    private String id;
    private String name;
    private String passwd;
}
