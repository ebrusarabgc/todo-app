package dev.sara.todoapp.controller;

import dev.sara.todoapp.entity.ApplicationUser;
import dev.sara.todoapp.request.AddUserRequest;
import dev.sara.todoapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ApplicationUser getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public ApplicationUser addUser(@RequestBody AddUserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
