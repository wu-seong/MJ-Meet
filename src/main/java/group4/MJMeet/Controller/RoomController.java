package group4.MJMeet.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") //CROS 해결을 위한 origin 추가
public class RoomController {
}
