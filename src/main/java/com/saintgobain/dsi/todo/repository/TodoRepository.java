package com.saintgobain.dsi.todo.repository;

import com.saintgobain.dsi.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    void deleteAllByCompleted(boolean completed);
}

