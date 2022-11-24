package group4.MJMeet.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserRepository userRepository;
    @RequestMapping("/login")
    public String login(Model model) {

        List<UserEntity> userEntityList = this.userRepository.findAll();
        model.addAttribute("userList", userEntityList);

        return "login";
    }
}