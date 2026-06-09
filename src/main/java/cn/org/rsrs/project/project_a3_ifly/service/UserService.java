package cn.org.rsrs.project.project_a3_ifly.service;

import cn.org.rsrs.project.project_a3_ifly.model.User;
import cn.org.rsrs.project.project_a3_ifly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getOrCreateUser(String username) {
        return userRepository.findByUsername(username)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(username);
                    return userRepository.save(newUser);
                });
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
