package dev.sara.todoapp.service;

import dev.sara.todoapp.entity.Todo;
import dev.sara.todoapp.entity.ApplicationUser;
import dev.sara.todoapp.repository.TodoRepository;
import dev.sara.todoapp.repository.UserRepository;
import dev.sara.todoapp.request.AddTodoRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TodoService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public void addTodo(Long userId, AddTodoRequest todoRequest) {
        ApplicationUser user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
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
        ApplicationUser user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Todo todo = todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);
        user.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }
}
