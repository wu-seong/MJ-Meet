package group4.MJMeet.User;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import java.util.List;
import org.springframework.ui.Model; // 모델 추가
import lombok.RequiredArgsConstructor; // 생성자
import org.springframework.web.bind.annotation.RestController;




@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final UserService userService;
    @RequestMapping("/login")
    public String login(Model model, @RequestParam(value="page", defaultValue="0") int page) {

        Page<UserEntity> paging = this.userService.getList(page);
        model.addAttribute("paging", paging);

        return "login";
    }

    @RequestMapping(value = "/userdetail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        UserEntity userEntity = this.userService.getUserEntity(id);
        model.addAttribute("userEntity", userEntity);
        return "userdetail";
    }


}