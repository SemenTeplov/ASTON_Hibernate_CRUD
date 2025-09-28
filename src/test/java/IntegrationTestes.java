import crud.controller.UserController;
import crud.dal.Repository;
import crud.service.UserService;
import crud.storage.UserStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MediaType;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(@ContextConfiguration(classes = IntegrationTestes.Config.class))
public class IntegrationTestes {
    String json = "{\"id\":3,\"name\":\"name\",\"email\":\"some@mail.com\",\"age\":43,\"created_at\":\"2025-09-25\"}";

    @Autowired
    WebApplicationContext wac;

    @Autowired
    CallableProcessingInterceptor callableInterceptor;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Configuration
    @EnableWebMvc
    static class Config implements WebMvcConfigurer {

        @Override
        public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
            configurer.registerCallableInterceptors(callableInterceptor());
        }

        @Bean
        public CallableProcessingInterceptor callableInterceptor() {
            return mock();
        }

        @Bean
        public Repository userRepository() {
            return Mockito.mock(Repository.class);
        }

        @Bean
        public UserStorage userStorage() {
            return Mockito.mock(UserStorage.class);
        }

        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }

        @Bean
        public UserController userController() {
            return Mockito.mock(UserController.class);
        }

    }

    @Test
    void createTest() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON_UTF_8))
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void readTest() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateTest() throws Exception {
        mockMvc.perform(put("/users")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON_UTF_8))
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/users")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON_UTF_8))
                        .content(json))
                .andExpect(status().isOk());
    }
}
