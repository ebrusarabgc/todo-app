package dev.sara.todoapp;

import dev.sara.todoapp.entity.Todo;
import dev.sara.todoapp.entity.Users;
import dev.sara.todoapp.repository.TodoRepository;
import dev.sara.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Users user = new Users();
		user.setPassword("should be hashed");
		user.setUsername("Sara");

		Todo todo = new Todo();
		todo.setContent("Upload video to YT");

		user.getTodoList().add(todo);

		todoRepository.save(todo);
		userRepository.save(user);
	}
}
