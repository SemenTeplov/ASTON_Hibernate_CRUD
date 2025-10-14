package crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@AllArgsConstructor
public class SwaggerConfig {
    private Environment environment;

    @Bean
    public OpenAPI defineOpenAPI() {
        Server server = new Server();
        String serverUrl = environment.getProperty("api.server.url");

        server.setUrl(serverUrl);
        server.setDescription("Development");

        Contact contact = new Contact();

        contact.setName("Name");
        contact.setEmail("some-email@mail.com");

        Info info = new Info()
                .title("System API for mange users")
                .version("1.0")
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
