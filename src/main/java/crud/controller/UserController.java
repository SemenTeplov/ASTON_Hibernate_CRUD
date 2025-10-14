package crud.controller;

import crud.model.User;
import crud.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @Tag(name = "post", description = "POST-Methods User API")
    @Operation(summary = "Create user", description = "Return created user object")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<User> create(@RequestBody User user) {
        return toHateoEntityModel(service.create(user));
    }

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<User> createEmail(@RequestBody User user) {
        return toHateoEntityModel(service.createEmail(user));
    }

    @Tag(name = "put", description = "PUT-Methods User API")
    @Operation(summary = "Update user", description = "Return updated user object")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<User> update(@RequestBody User user) {
        return toHateoEntityModel(service.update(user));
    }

    @Tag(name = "get", description = "GET-Methods User API")
    @Operation(summary = "Get user", description = "Return got user object")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<User> read(@PathVariable(name = "id") Integer id) {
        return toHateoEntityModel(service.read(id));
    }

    @Tag(name = "delete", description = "DELETE-Methods User API")
    @Operation(summary = "Delete user", description = "Return deleted user object")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public User delete(@RequestBody User user) {
        return service.delete(user);
    }

    @DeleteMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<User> deleteMail(@RequestBody User user) {
        return toHateoEntityModel(service.deleteEmail(user));
    }

    private EntityModel<User> toHateoEntityModel(User user) {
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).read(user.getId()))
                .withSelfRel();

        return EntityModel.of(user, link);
    }
}
