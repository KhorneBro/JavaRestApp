package com.app.AppJava.controllers;

import com.app.AppJava.models.User;
import com.app.AppJava.payload.response.MessageResponse;
import com.app.AppJava.repository.UserRepository;
import com.app.AppJava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        try {
            return ResponseEntity.ok().body(userService.allUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Empty"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.oneUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("User doesn't exist"));
//            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/create")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        if (userService.createUserValidation(user).equals("Ok")) {
            try {
                userService.createUser(user);
                return ResponseEntity.ok().body(new MessageResponse("User created"));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new MessageResponse("Some troubles with creating"));
            }
        } else {
            return ResponseEntity.badRequest().body(userService.createUserValidation(user));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @PathVariable Long id, @RequestBody User user) {
        if (userService.createUserValidation(user).equals("Ok")) {
            try {
                userService.updateUser(user, id);
                return ResponseEntity.ok().body(new MessageResponse("User update"));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new MessageResponse("Some troubles with creating"));
            }
        } else {
            return ResponseEntity.badRequest().body(userService.createUserValidation(user));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().body(new MessageResponse("User deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Troubles"));
        }
    }
}
