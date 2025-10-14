package crud.controller;

import crud.model.User;
import crud.service.UserService;

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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<User> update(@RequestBody User user) {
        return toHateoEntityModel(service.update(user));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<User> read(@PathVariable(name = "id") Integer id) {
        return toHateoEntityModel(service.read(id));
    }

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
