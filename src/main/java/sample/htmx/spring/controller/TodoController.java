package sample.htmx.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import sample.htmx.spring.model.TodoItem;
import sample.htmx.spring.service.TodoService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class TodoController {

    private static final Logger LOG = LoggerFactory.getLogger(TodoController.class);

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    public Mono<ServerResponse> index(ServerRequest request) {
        LOG.debug("index");
        return returnTodos(request, "index");
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        LOG.debug("list");
        return returnTodos(request, "todos/list");
    }

    public Mono<ServerResponse> insert(ServerRequest request) {
        LOG.debug("insert");
        return request.body(BodyExtractors.toFormData()).flatMap(form -> {
            TodoItem item = TodoItem.fromRequest(form);
            service.insert(item);
            LOG.debug("{}", item);
            return returnTodos(request, "todos/list");
        });
    }

    public Mono<ServerResponse> find(ServerRequest request) {
        LOG.debug("find");
        Optional<TodoItem> todo = service.find(Long.valueOf(request.pathVariable("id")));
        if (todo.isPresent()) {
            var model = new HashMap<String, TodoItem>();
            model.put("todo", todo.get());
            return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("todos/detail", model);
        } else
            return ServerResponse.notFound().build();
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        LOG.debug("update");
        return request.body(BodyExtractors.toFormData()).flatMap(form -> {
            TodoItem item = TodoItem.fromRequest(form);
            Long id = Long.valueOf(request.pathVariable("id"));
            item.setId(id);
            service.update(item);
            LOG.debug("{}", item);
            return returnTodos(request, "todos/list");
        });
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        LOG.debug("delete");
        Long id = Long.valueOf(request.pathVariable("id"));
        service.delete(id);
        return returnTodos(request, "todos/list");
    }

    private Mono<ServerResponse> returnTodos(ServerRequest request, String template) {
        List<TodoItem> todos = service.listTodos(request.queryParam("q"));
        var model = new HashMap<String, List<TodoItem>>();
        model.put("todos", todos);
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render(template, model);
    }
}
