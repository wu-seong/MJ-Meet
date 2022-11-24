package group4.MJMeet;

import group4.MJMeet.User.UserEntity;
import group4.MJMeet.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@SpringBootTest
class MjMeetApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {



		UserEntity q1 = new UserEntity();
		q1.setPassword("jimin");
		q1.setUsername("지민");
		this.userRepository.save(q1);

		UserEntity q2 = new UserEntity();
		q2.setPassword("jimin1");
		q2.setUsername("지민2");
		this.userRepository.save(q2);

//		Optional<UserEntity> oq = this.userRepository.findById(0);
//		if(oq.isPresent()) {
//			UserEntity q = oq.get();
//			assertEquals("지민", q.getUsername());
//			System.out.print(q);
//		}

	}

}
