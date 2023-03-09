package dev.sara.todoapp.repository;

import dev.sara.todoapp.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
}
