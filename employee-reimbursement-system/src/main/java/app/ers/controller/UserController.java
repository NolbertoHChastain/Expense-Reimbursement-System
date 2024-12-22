package app.ers.controller;

import app.ers.model.Reimbursement;
import app.ers.model.User;
import app.ers.model.DTO.IncomingUserDTO;
import app.ers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*@GetMapping("/{userId}/reimbursements/{status}")
    public ResponseEntity<List<Reimbursement>> getAllReimbursementsByStatus(
            @PathVariable int userId,
            @PathVariable String status) {
        // employee : sees only theirs
        // manager : sees all
    }*/

}