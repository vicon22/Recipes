package com.example.java_spring.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> register(@RequestBody User user) {

       if (userRepo.findByEmail(user.getEmail()).isEmpty()){
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
           return ResponseEntity.ok().build();
       }
       return ResponseEntity.badRequest().build();
    }

    @GetMapping("/api/users/all")
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

}
