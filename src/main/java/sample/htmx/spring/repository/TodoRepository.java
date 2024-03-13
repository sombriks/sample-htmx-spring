package sample.htmx.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.htmx.spring.model.TodoItem;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Long> {

    List<TodoItem> findByDescriptionContainingIgnoreCase(String description);
}
