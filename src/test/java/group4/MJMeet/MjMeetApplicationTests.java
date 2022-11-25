package group4.MJMeet;

import group4.MJMeet.User.UserEntity;
import group4.MJMeet.User.UserRepository;
import group4.MJMeet.User.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@SpringBootTest
class MjMeetApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {



		for(int i = 0; i <= 200;i ++) {
			String password = String.format("테스트 데이터입니다:[%03d]", i);
			String username = "내용무";
			this.userService.create(password, username);
		}

	}

}
