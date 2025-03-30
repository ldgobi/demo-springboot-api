package demo.service;

import demo.model.User;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            throw new IllegalArgumentException("User ID already exists...");
        }
        userRepository.save(user);
    }
}