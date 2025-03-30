package demo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication(scanBasePackages = "demo")
@OpenAPIDefinition
@EnableJpaRepositories(basePackages = "demo.repository")
@EntityScan(basePackages = "demo.model")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
