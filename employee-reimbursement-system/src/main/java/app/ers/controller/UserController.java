package app.ers.controller;

import java.util.List;

import app.ers.model.User;
import app.ers.model.DTO.IncomingUserDTO;
import app.ers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "https://hoppscotch.io")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    } // constructor injection

    @PostMapping
    public ResponseEntity<User> register(@RequestBody IncomingUserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.register(userDTO)); // 201 - successful resource creation
    }

    @GetMapping("/{userId}") // remove {userId} once jwt/session setup
    public ResponseEntity<List<User>> getAllUsers(@PathVariable int userId) {
        return ResponseEntity.ok().body(userService.getAllUsers(userId));
    }

}