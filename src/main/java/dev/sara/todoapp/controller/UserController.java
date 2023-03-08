package dev.sara.todoapp.controller;

import dev.sara.todoapp.entity.Todo;
import dev.sara.todoapp.entity.Users;
import dev.sara.todoapp.repository.TodoRepository;
import dev.sara.todoapp.repository.UserRepository;
import dev.sara.todoapp.request.AddTodoRequest;
import dev.sara.todoapp.request.AddUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;
    private TodoRepository todoRepository;

    public UserController(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping
    public Users addUser(@RequestBody AddUserRequest userRequest) {
        Users user = new Users();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        return userRepository.save(user);
    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest) {
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        user.getTodoList().add(todo);
        todoRepository.save(todo);
        userRepository.save(user);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted(@PathVariable Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }

    @DeleteMapping("/{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
        Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        user.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
    }

}
