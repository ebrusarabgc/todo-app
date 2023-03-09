package dev.sara.todoapp.service;

import dev.sara.todoapp.entity.ApplicationUser;
import dev.sara.todoapp.repository.UserRepository;
import dev.sara.todoapp.request.AddUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ApplicationUser getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    public ApplicationUser addUser(AddUserRequest userRequest) {
        ApplicationUser user = new ApplicationUser();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        ApplicationUser user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
    }
}
