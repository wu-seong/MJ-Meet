package group4.MJMeet.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import org.springframework.ui.Model; // 모델 추가
import lombok.RequiredArgsConstructor; // 생성자


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    @RequestMapping("/user")
    public String login(Model model, @RequestParam(value="page", defaultValue="0") int page) {

        Page<UserEntity> paging = this.userService.getList(page);
        model.addAttribute("paging", paging);

        return "user";
    }

    @RequestMapping(value = "/userdetail/{id}")
    public String detail(Model model, @PathVariable("id") Integer num) {
        UserEntity userEntity = this.userService.getUserEntity(num);
        model.addAttribute("userEntity", userEntity);
        return "userdetail";
    }


}