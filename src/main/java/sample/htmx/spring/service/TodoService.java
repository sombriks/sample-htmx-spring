package sample.htmx.spring.service;

import org.springframework.stereotype.Service;
import sample.htmx.spring.model.TodoItem;
import sample.htmx.spring.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<TodoItem> listTodos(Optional<String> q) {
        return repository.findByDescriptionContainingIgnoreCase(q.orElse(""));
    }

    public TodoItem insert(TodoItem item) {
        return repository.save(item);
    }

    public Optional<TodoItem> find(Long id) {
        return repository.findById(id);
    }

    public TodoItem update(TodoItem item) {
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
