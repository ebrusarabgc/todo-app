package dev.sara.todoapp.controller;

import dev.sara.todoapp.entity.Users;
import dev.sara.todoapp.request.AddTodoRequest;
import dev.sara.todoapp.request.AddUserRequest;
import dev.sara.todoapp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public Users addUser(@RequestBody AddUserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest) {
        userService.addTodo(userId, todoRequest);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted(@PathVariable Long todoId) {
        userService.toggleTodoCompleted(todoId);
    }

    @DeleteMapping("/{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
        userService.deleteTodo(userId, todoId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
