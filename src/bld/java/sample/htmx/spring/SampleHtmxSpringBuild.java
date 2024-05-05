package sample.htmx.spring;

import rife.bld.WebProject;

public class SampleHtmxSpringBuild extends WebProject {

    public SampleHtmxSpringBuild() {

        pkg = "sample.htmx.spring";
        name = "SampleHtmxSpring";
        mainClass = "sample.htmx.spring.SampleHtmxSpringApplication";
        version = version(0, 0, 1, "SNAPSHOT");

        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);

        scope(compile)
                .include(dependency("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5"))
                .include(dependency("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.5"))
                .include(dependency("org.springframework.boot:spring-boot-starter-webflux:3.2.5"))
                .include(dependency("org.liquibase:liquibase-core:4.24.0"))
                .include(dependency("org.webjars.npm:htmx.org:2.0.0-alpha2:3.2.5"));

//        scope(provided);

        scope(test)
                .include(dependency("org.springframework.boot:spring-boot-starter-test:3.2.5"))
                .include(dependency("io.projectreactor:reactor-test:3.6.5"))
                .include(dependency("org.hamcrest:hamcrest:2.2"));

        scope(standalone)
                .include(dependency("org.springframework.boot:spring-boot-loader:3.2.5"))
                .include(dependency("com.h2database:h2:2.2.224"));
    }
}
