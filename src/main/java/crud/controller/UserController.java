package crud.controller;

import crud.model.User;
import crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User read(@PathVariable(name = "id") Integer id) {
        return service.read(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public User delete(@RequestBody User user) {
        return service.delete(user);
    }
}
