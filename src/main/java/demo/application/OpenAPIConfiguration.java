package demo.application;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8081");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Luciano Gobi");
        myContact.setEmail("gobi.luciano@google.com");

        Info information = new Info()
                .title("Demo Application")
                .version("1.0")
                .description("This API exposes endpoints created from code generated.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}