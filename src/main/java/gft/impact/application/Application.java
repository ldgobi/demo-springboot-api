package gft.impact.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication(scanBasePackages = "gft.impact")
@OpenAPIDefinition
@EnableJpaRepositories(basePackages = "gft.impact.proposal.repository")
@EntityScan(basePackages = "gft.impact.proposal.model")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
