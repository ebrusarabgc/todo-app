package dev.sara.todoapp.service;

import dev.sara.todoapp.entity.Todo;
import dev.sara.todoapp.entity.Users;
import dev.sara.todoapp.repository.TodoRepository;
import dev.sara.todoapp.repository.UserRepository;
import dev.sara.todoapp.request.AddTodoRequest;
import dev.sara.todoapp.request.AddUserRequest;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public UserService(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public Users getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    public Users addUser(AddUserRequest userRequest) {
        Users user = new Users();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        return userRepository.save(user);
    }

    public void addTodo(Long userId, AddTodoRequest todoRequest) {
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        user.getTodoList().add(todo);
        todoRepository.save(todo);
        userRepository.save(user);
    }

    public void toggleTodoCompleted(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }

    public void deleteTodo(Long userId, Long todoId){
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Todo todo = todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);
        user.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }

    public void deleteUser(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
    }


}
