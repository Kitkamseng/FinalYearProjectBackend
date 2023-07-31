package com.kitkamseng.votebackend.controller;

import com.kitkamseng.votebackend.exception.UserNameNotFoundException;
import com.kitkamseng.votebackend.exception.UserNotFoundException;
import com.kitkamseng.votebackend.model.User;
import com.kitkamseng.votebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @GetMapping("/user/by-username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNameNotFoundException(username);
        }
        return user;
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully.";
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(user == null || !user.getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = generateToken(user.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    private String generateToken(String username){
        return "Login Success";
    }

}
