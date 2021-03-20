package com.saintgobain.dsi.todo.endpoint;

import com.saintgobain.dsi.todo.domain.Todo;
import com.saintgobain.dsi.todo.repository.TodoRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
@Transactional
@CrossOrigin(origins = {"https://stgodsidptodoui.z6.web.core.windows.net", "http://localhost:4200"})
public class TodoEndpoint {

    private final TodoRepository todoRepository;

    @GetMapping
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todo) {
        Todo entity = todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        entity.setTitle(todo.getTitle());
        entity.setCompleted(todo.isCompleted());
        entity.setOrder(todo.getOrder());
        return todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Todo entity = todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        todoRepository.delete(entity);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompleted() {
        todoRepository.deleteAllByCompleted(true);
    }

}
