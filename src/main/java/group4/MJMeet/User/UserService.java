package group4.MJMeet.User;

import java.util.List;

import group4.MJMeet.User.UserEntity;
import group4.MJMeet.User.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import group4.MJMeet.DataNotFoundException;

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
}

