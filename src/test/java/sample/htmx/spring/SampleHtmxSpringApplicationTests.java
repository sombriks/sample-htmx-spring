package sample.htmx.spring;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.htmx.spring.service.TodoService;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
class SampleHtmxSpringApplicationTests {

	@Autowired
	private TodoService service;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldListTodos(){
		var result = service.listTodos(Optional.of(""));
		MatcherAssert.assertThat(result, Matchers.notNullValue());
		MatcherAssert.assertThat(result, Matchers.hasSize(3));
	}

}
