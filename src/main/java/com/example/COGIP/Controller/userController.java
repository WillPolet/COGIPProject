package com.example.COGIP.Controller;
import com.example.COGIP.Services.UserService;
import jakarta.validation.Valid;


import com.example.COGIP.Repository.UserRepository;
import com.example.COGIP.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class userController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService; // Inject the UserService


    @GetMapping("/users") //Get all users
    public List<User> getAllUsers(){return userRepository.findAll();}

    @GetMapping("/user/{name}")
    public User getUser(@PathVariable (value = "name")String username){
        return userRepository.findByUsername(username);
    }
    @PostMapping("/new/user") // Create a new user
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        String username = user.getUsername();
        if (userService.verifyIfUsernameExist(username)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already in use.");
        }
        else {
        String plainPassword = user.getPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @PutMapping("/user/modify/{name}")
    public ResponseEntity<User> updateUser(@PathVariable(value="name") String name
            , @Valid @RequestBody User userDetails) {User user = userRepository.findByUsername(name);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Returns a 404 Not Found response
        }
        user.setUsername(userDetails.getUsername());
        String plainPassword = userDetails.getPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        user.setRole(userDetails.getRole());
        final User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/user/delete/{name}")
    public Map<String, Boolean> deleteDoctor(@PathVariable(value = "name") String name)
    {User user = userRepository.findByUsername(name);
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
