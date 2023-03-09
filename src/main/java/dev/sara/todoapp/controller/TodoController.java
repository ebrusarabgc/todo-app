package dev.sara.todoapp.controller;

import dev.sara.todoapp.request.AddTodoRequest;
import dev.sara.todoapp.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest) {
        todoService.addTodo(userId, todoRequest);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted(@PathVariable Long todoId) {
        todoService.toggleTodoCompleted(todoId);
    }

    @DeleteMapping("/{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
        todoService.deleteTodo(userId, todoId);
    }
}
