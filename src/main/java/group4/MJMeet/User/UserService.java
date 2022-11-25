package group4.MJMeet.User;

import java.util.List;

import group4.MJMeet.User.UserEntity;
import group4.MJMeet.User.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import group4.MJMeet.DataNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getList() {
        return this.userRepository.findAll();
    }

    public UserEntity getUserEntity(Integer id) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        if (userEntity.isPresent()) {
            return userEntity.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String password, String username) {
        UserEntity u = new UserEntity();
        u.setPassword(password);
        u.setUsername(username);
        this.userRepository.save(u);
    }

    public Page<UserEntity> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10);
        return this.userRepository.findAll(pageable);
    }
}

