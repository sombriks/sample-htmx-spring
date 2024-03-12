package sample.htmx.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration(proxyBeanMethods = false)
public class RoutesConfiguration {

    @Bean
    public RouterFunction<ServerResponse> buildApi(TodoController controller) {
        return RouterFunctions.route()
                .GET("/", controller::index)
                .path("/todos", todos -> todos
                        .GET(controller::list)
                        .POST(controller::insert)
                        .path("/{id}", id -> id
                                .GET(controller::find)
                                .PUT(controller::update)
                                .DELETE(controller::delete)
                        )
                ).build();
    }
}
