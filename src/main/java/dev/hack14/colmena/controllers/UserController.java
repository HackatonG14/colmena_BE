package dev.hack14.colmena.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import dev.hack14.colmena.dtos.UserDto;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.services.UserService;


import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
   
    @Autowired
    private UserService userService;
   
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
   
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
   
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
   
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }
   
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}