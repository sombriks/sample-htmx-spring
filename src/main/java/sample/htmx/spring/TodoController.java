package sample.htmx.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TodoController {
    public Mono<ServerResponse> index(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("index");
    }

    public Mono<ServerResponse> list(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> insert(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> find(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return null;
    }
}
