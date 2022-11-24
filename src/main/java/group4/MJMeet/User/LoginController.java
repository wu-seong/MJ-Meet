package group4.MJMeet.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import org.springframework.ui.Model; // 모델 추가
import lombok.RequiredArgsConstructor; // 생성자

import org.springframework.web.bind.annotation.PathVariable; // 경로에 변수


@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final UserService userService;
    @RequestMapping("/login")
    public String login(Model model) {

        List<UserEntity> userEntityList = this.userRepository.findAll();
        model.addAttribute("userList", userEntityList);

        return "login";
    }

    @RequestMapping(value = "/userdetail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        UserEntity userEntity = this.userService.getUserEntity(id);
        model.addAttribute("userEntity", userEntity);
        return "userdetail";
    }
}