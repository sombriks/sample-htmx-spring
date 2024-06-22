package sample.htmx.spring;

import rife.bld.BuildCommand;
import rife.bld.WebProject;
import rife.bld.extension.BootJarOperation;

import java.util.List;

import static rife.bld.dependencies.Repository.*;
import static rife.bld.dependencies.Scope.*;
import static rife.bld.operations.TemplateType.*;

public class SampleHtmxSpringBuild extends WebProject {

    public SampleHtmxSpringBuild() {

        pkg = "sample.htmx.spring";
        name = "SampleHtmxSpring";
        mainClass = "sample.htmx.spring.SampleHtmxSpringApplication";
        version = version(0, 0, 1, "SNAPSHOT");

        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);

        scope(compile)
                .include(dependency("org.springframework.boot:spring-boot-starter-data-jpa:3.3.1"))
                .include(dependency("org.springframework.boot:spring-boot-starter-thymeleaf:3.3.1"))
                .include(dependency("org.springframework.boot:spring-boot-starter-webflux:3.3.1"))
                .include(dependency("org.webjars.npm:htmx.org:1.9.12"))
                .include(dependency("org.liquibase:liquibase-core:4.28.0"));

        // scope(provided);

        scope(test)
                .include(dependency("org.junit.platform:junit-platform-console-standalone:1.10.2"))
                .include(dependency("org.springframework.boot:spring-boot-starter-test:3.3.1"))
                .include(dependency("io.projectreactor:reactor-test:3.6.7"))
                .include(dependency("org.hamcrest:hamcrest:2.2"));

        scope(standalone)
                .include(dependency("org.springframework.boot:spring-boot-loader:3.3.1"))
                .include(dependency("com.h2database:h2:2.2.224"));
    }

    @BuildCommand(summary = "Creates an executable JAR for the project")
    public void bootjar() throws Exception {
        new BootJarOperation()
                .fromProject(this)
                .execute();
    }

    public static void main(String[] args) {
        new SampleHtmxSpringBuild().start(args);
    }
}
