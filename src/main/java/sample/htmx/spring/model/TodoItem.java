package sample.htmx.spring.model;

import jakarta.persistence.*;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "todos")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Boolean done;

    private LocalDateTime created;

    public TodoItem() {
    }

    public TodoItem(String description, LocalDateTime created, boolean done, Long id) {
        this.description = description;
        this.created = created;
        this.done = done;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public static TodoItem fromRequest(MultiValueMap<String, String> form) {
        String description = form.getFirst("description");
        boolean done = false;
        var doneMaybe = Optional.ofNullable(form.getFirst("done"));
        if (doneMaybe.isPresent()) done = Boolean.parseBoolean(doneMaybe.get());
        LocalDateTime created = LocalDateTime.now();
        var createdMaybe = Optional.ofNullable(form.getFirst("created"));
        if (createdMaybe.isPresent()) created = LocalDateTime.parse(createdMaybe.get());
        Long id = null;
        var idMaybe = Optional.ofNullable(form.getFirst("id"));
        if (idMaybe.isPresent()) id = Long.parseLong(idMaybe.get());
        return new TodoItem(description, created, done, id);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", created=" + created +
                '}';
    }
}
